package patterns.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patterns.users.config.sequrity.CurrentUserId;
import patterns.users.data.dto.request.PassportDto;
import patterns.users.data.dto.request.PassportUpdateDto;
import patterns.users.service.PassportService;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PassportDto> getPassport(@PathVariable String userId) {
        PassportDto passportDto = passportService.getPassport(userId);
        return ResponseEntity.ok(passportDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updatePassport(@PathVariable String userId,
                                                   @RequestBody PassportUpdateDto passportUpdateDto) {
        passportService.updatePassport(userId, passportUpdateDto);
        return ResponseEntity.ok("Passport updated successfully");
    }

    @GetMapping("/my")
    public ResponseEntity<PassportDto> getMyPassport(@CurrentUserId String userId) {
        PassportDto passportDto = passportService.getPassport(userId);
        return ResponseEntity.ok(passportDto);
    }

    @PutMapping("/my")
    public ResponseEntity<String> updateMyPassport(@CurrentUserId String userId,
                                                 @RequestBody PassportUpdateDto passportUpdateDto) {
        passportService.updatePassport(userId, passportUpdateDto);
        return ResponseEntity.ok("Passport updated successfully");
    }
}
