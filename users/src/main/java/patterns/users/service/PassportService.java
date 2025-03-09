package patterns.users.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import patterns.users.data.dto.request.PassportDto;
import patterns.users.data.dto.request.PassportUpdateDto;
import patterns.users.data.entity.PassportEntity;
import patterns.users.data.entity.UserEntity;
import patterns.users.data.repository.PassportRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassportService {

    @Lazy
    @Autowired
    private UserService userService;

    private final PassportRepository passportRepository;
    
    public PassportEntity createNewPassport(PassportDto dto) {
        return passportRepository.save(hydratePassportEntity(dto));
    }

    public PassportDto getPassport(String userId) {
        UserEntity user = userService.findById(userId);

        PassportEntity passport = passportRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Passport not found"));

        return hydratePassportDto(passport);
    }

    public void updatePassport(String userId, PassportUpdateDto passportUpdateDto) {
        UserEntity user = userService.findById(userId);

        PassportEntity passport = passportRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Passport not found"));

        hydratePassportEntityOnUpdate(passport, passportUpdateDto);
        passportRepository.save(passport);
    }

    private PassportDto hydratePassportDto(PassportEntity passport) {
        PassportDto dto = new PassportDto();
        dto.setSeries(passport.getSeries());
        dto.setNumber(passport.getNumber());
        dto.setIssueDate(passport.getIssueDate().toString());
        dto.setDepartmentCode(passport.getDepartmentCode());
        dto.setDepartmentName(passport.getDepartmentName());
        return dto;
    }
    
    private PassportEntity hydratePassportEntity(PassportDto dto) {
        PassportEntity passport = new PassportEntity();
        passport.setId(String.valueOf(UUID.randomUUID()));
        passport.setSeries(dto.getSeries());
        passport.setNumber(dto.getNumber());
        passport.setIssueDate(LocalDate.parse(dto.getIssueDate()));
        passport.setDepartmentCode(dto.getDepartmentCode());
        passport.setDepartmentName(dto.getDepartmentName());
        return passport;
    }

    private void hydratePassportEntityOnUpdate(PassportEntity passport, PassportUpdateDto dto) {
        passport.setSeries(dto.getSeries());
        passport.setNumber(dto.getNumber());
        passport.setIssueDate(dto.getIssueDate());
        passport.setDepartmentCode(dto.getDepartmentCode());
        passport.setDepartmentName(dto.getDepartmentName());
    }
}
