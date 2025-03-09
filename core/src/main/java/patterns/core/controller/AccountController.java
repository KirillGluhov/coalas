package patterns.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patterns.core.data.dto.request.MoneyDto;
import patterns.core.data.dto.response.AccountDto;
import patterns.core.service.AccountService;
import patterns.core.service.communication.UserServiceClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final UserServiceClient userServiceClient;

    @GetMapping("/test")
    public String hello() {
        return "Core said you HELLO!";
    }

    @GetMapping("/check/{accountId}")
    public boolean checkAccount(@RequestHeader("Authorization") String authHeader, @PathVariable String accountId) {
        String userId = userServiceClient.getUserId(authHeader);
        return accountService.checkAccount(userId, accountId);
    }

    @GetMapping("/clients")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/clients/{userId}")
    public List<AccountDto> getAllAccountsOfUser(@PathVariable String userId) {
        return accountService.getAllAccountsByUserId(userId);
    }

    @GetMapping("/clients/{accountId}")
    public AccountDto getAccountById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId, null);
    }

    @GetMapping("/my")
    public List<AccountDto> getAllMyAccounts(@RequestHeader("Authorization") String authHeader) {
        String userId = userServiceClient.getUserId(authHeader);
        return accountService.getAllAccountsByUserId(userId);
    }

    @GetMapping("/my/{accountId}")
    public AccountDto getMyAccountById(@RequestHeader("Authorization") String authHeader,
                                          @PathVariable String accountId) {
        String userId = userServiceClient.getUserId(authHeader);
        return accountService.getAccountById(accountId, userId);
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<Map<String, String>> withdraw(@PathVariable String accountId, @RequestBody MoneyDto moneyDto) {
        accountService.withdraw(accountId, moneyDto.getMoney());
        return ResponseEntity.ok(Map.of("status", "success", "message", "Money withdrawn successfully"));
    }

    @PostMapping("/{accountId}/replenish")
    public ResponseEntity<Map<String, String>> replenish(@PathVariable String accountId, @RequestBody MoneyDto moneyDto) {
        accountService.replenish(accountId, moneyDto.getMoney());
        return ResponseEntity.ok(Map.of("status", "success", "message", "Money replenished successfully"));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String accountId) {
        accountService.delete(accountId);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Account deleted successfully"));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createAccount(@RequestHeader("Authorization") String authHeader) {
        String userId = userServiceClient.getUserId(authHeader);
        accountService.createAccount(userId);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Account created successfully"));
    }

}
