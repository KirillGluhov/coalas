package patterns.users.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import patterns.users.config.sequrity.JwtProvider;
import patterns.users.data.dto.request.*;
import patterns.users.data.dto.response.TokenDto;
import patterns.users.data.dto.response.TokenWithRoleDto;
import patterns.users.data.dto.response.UserInfoDto;
import patterns.users.data.entity.*;
import patterns.users.data.enums.Gender;
import patterns.users.data.enums.Role;
import patterns.users.data.repository.ClientRepository;
import patterns.users.data.repository.EmployeeRepository;
import patterns.users.data.repository.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenService tokenService;
    private final PositionService positionService;
    private final PassportService passportService;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    private static final int LENGTH_OF_PASSWORD = 12;

    public String getUserId(String header) {
        String id = tokenService.extractUserId((StringUtils.hasText(header) && header.startsWith("Bearer ")) ? header.substring(7) : null);
        UserEntity user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return user.getId();
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void blockUser(String userId) {
        UserEntity user = findById(userId);
        user.setBlocked(!user.isBlocked());
        userRepository.save(user);
    }

    public UserInfoDto getUserInfo(String userId) {
        UserEntity user = findById(userId);
        return hydrateUserInfo(user);
    }

    public List<UserInfoDto> getUsersByRole(Role role) {
        List<UserEntity> users = userRepository.findByRole(role);
        return users.stream().map(this::hydrateUserInfo).collect(Collectors.toList());
    }

    public TokenDto registerUser(UserRegistrationDto dto) {
        if (dto.getRole() == Role.EMPLOYEE) {
            return registerEmployee(dto);
        } else if (dto.getRole() == Role.CLIENT) {
            return registerClient(dto);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }
    }

    public TokenWithRoleDto login(LoginDto loginDto) {
        UserEntity user = new UserEntity();
        if (loginDto.getPhone() != null)
        {
            user = userRepository.findByPhone(loginDto.getPhone());
        }
        else if (loginDto.getEmail() != null)
        {
            user = userRepository.findByEmail(loginDto.getEmail());
        }
        else
        {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (user == null)
            throw new BadCredentialsException("Invalid credentials");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        TokenDto tokens = tokenService.getTokens(user);
        return hydrateTokenWithRoleDto(user, tokens);
    }

    public void updateUser(String userId, UserUpdateDto dto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setSecondName(dto.getSecondName());
        user.setGender(Gender.valueOf(dto.getGender()));
        user.setBirthDate(LocalDate.parse(dto.getBirthDate()));
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        userRepository.save(user);

        if (dto.getPositionId() != null) {
            PositionEntity position = positionService.getPositionById(dto.getPositionId());
            EmployeeEntity employee = employeeRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            employee.setPosition(position);
            employeeRepository.save(employee);
        }
    }

    public void changePassword(String userId, ChangePasswordDto changePasswordDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(changePasswordDto.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    private TokenDto registerEmployee(UserRegistrationDto dto) {
        PositionEntity position = positionService.getPositionById(dto.getPositionId());
        UserEntity user = hydrateUserEntity(dto);
        userRepository.save(user);
        employeeRepository.save(hydrateEmployeeEntity(user, position));
        return tokenService.getTokens(user);
    }

    private TokenDto registerClient(UserRegistrationDto dto) {
        PassportEntity passport = passportService.createNewPassport(dto.getPassport());
        UserEntity user = hydrateUserEntity(dto);
        userRepository.save(user);
        clientRepository.save(hydrateClientEntity(user, passport));
        return new TokenDto("","");
    }

    private UserEntity hydrateUserEntity(UserRegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setId(String.valueOf(UUID.randomUUID()));
        user.setName(registrationDto.getName());
        user.setLastName(registrationDto.getLastName());
        user.setSecondName(registrationDto.getSecondName());
        user.setGender(Gender.valueOf(registrationDto.getGender()));
        user.setBirthDate(LocalDate.parse(registrationDto.getBirthDate()));
        if (registrationDto.getPassword() == null) {
            registrationDto.setPassword(generatePassword());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(registrationDto.getPassword()));
        user.setRole(registrationDto.getRole());
        user.setPhone(registrationDto.getPhone());
        user.setEmail(registrationDto.getEmail());
        return user;
    }

    private UserInfoDto hydrateUserInfo(UserEntity user) {
        UserInfoDto response = new UserInfoDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastName(user.getLastName());
        response.setSecondName(user.getSecondName());
        response.setGender(user.getGender());
        response.setBirthDate(user.getBirthDate());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        if (user.getRole() == Role.CLIENT) {
            response.setPassportId(user.getId());
        } else if (user.getRole() == Role.EMPLOYEE) {
            response.setPositionId(user.getId());
        }
        return response;
    }

    private EmployeeEntity hydrateEmployeeEntity(UserEntity user, PositionEntity position) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(user.getId());
        employee.setPosition(position);
        return employee;
    }

    private ClientEntity hydrateClientEntity(UserEntity user, PassportEntity passport) {
        ClientEntity client = new ClientEntity();
        client.setId(user.getId());
        client.setPassport(passport);
        return client;
    }

    private TokenWithRoleDto hydrateTokenWithRoleDto(UserEntity user, TokenDto tokenDto) {
        TokenWithRoleDto response = new TokenWithRoleDto();
        response.setAccessToken(tokenDto.getAccessToken());
        response.setRefreshToken(tokenDto.getRefreshToken());
        response.setUserType(user.getRole());
        return response;
    }

    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH_OF_PASSWORD);
        for (int i = 0; i < LENGTH_OF_PASSWORD; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }


}
