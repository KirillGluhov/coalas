package patterns.users.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patterns.users.config.sequrity.CurrentUserId;
import patterns.users.data.dto.request.*;
import patterns.users.data.dto.response.TokenDto;
import patterns.users.data.dto.response.TokenWithRoleDto;
import patterns.users.data.dto.response.UserInfoDto;
import patterns.users.data.enums.Role;
import patterns.users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String hello() {
        return "Users said you HELLO!";
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUserId(@RequestHeader("Authorization") String authHeader) {
        String userId = userService.getUserId(authHeader);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable(required = false) String userId,
                                                   @CurrentUserId String currentUserId) {
        UserInfoDto response = userService.getUserInfo(userId != null ? userId : currentUserId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/block")
    public ResponseEntity<Void> blockUser(@PathVariable String userId) {
        userService.blockUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<UserInfoDto>> getUsersByRole(@RequestParam Role role) {
        List<UserInfoDto> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<TokenDto> registerUser(@RequestBody UserRegistrationDto dto) {
        TokenDto response = userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody UserUpdateDto dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto,
                                                 @CurrentUserId String userId) {
        userService.changePassword(userId, changePasswordDto);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenWithRoleDto> login(@RequestBody LoginDto loginDto) {
        TokenWithRoleDto loginResponse = userService.login(loginDto);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }




}
