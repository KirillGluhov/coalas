package patterns.loans.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patterns.loans.data.dto.request.CreateLoanDto;
import patterns.loans.data.dto.request.PurchaseDto;
import patterns.loans.data.dto.response.LoanDto;
import patterns.loans.data.entity.LoanEntity;
import patterns.loans.service.LoanService;
import patterns.loans.service.communication.AccountServiceClient;
import patterns.loans.service.communication.UserServiceClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final UserServiceClient userServiceClient;
    private final AccountServiceClient accountServiceClient;

    @GetMapping("/test")
    public String hello() {
        return "Loans said you HELLO!";
    }

    @GetMapping("/clients")
    public List<LoanDto> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/clients/{userId}")
    public List<LoanDto> getAllLoansOfUser(@PathVariable String userId) {
        return loanService.getAllLoansByUserId(userId);
    }

    @GetMapping("/clients/{LoanId}")
    public LoanDto getLoanById(@PathVariable String LoanId) {
        return loanService.getLoanById(LoanId, null);
    }

    @GetMapping("/my")
    public List<LoanDto> getAllMyLoans(@RequestHeader("Authorization") String authHeader) {
        String userId = userServiceClient.getUserId(authHeader);
        return loanService.getAllLoansByUserId(userId);
    }

    @GetMapping("/my/{LoanId}")
    public LoanDto getMyLoanById(@RequestHeader("Authorization") String authHeader,
                                          @PathVariable String LoanId) {
        String userId = userServiceClient.getUserId(authHeader);
        return loanService.getLoanById(LoanId, userId);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createLoan(@RequestHeader("Authorization") String authHeader,
                                                          @RequestBody CreateLoanDto loanDto) {
        String userId = userServiceClient.getUserId(authHeader);
        boolean check = accountServiceClient.checkAccountForLoan(loanDto.getAccountId(), userId);
        if (check) {
            loanService.createLoan(loanDto, userId);
            return ResponseEntity.ok(Map.of("status", "success", "message", "Loan created successfully"));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{loanId}/accounts/{accountId}/autodebt")
    public ResponseEntity<Map<String, String>> enableAutodebt(@RequestHeader("Authorization") String authHeader,
                                                              @PathVariable String loanId,
                                                              @PathVariable String accountId) {
        String userId = userServiceClient.getUserId(authHeader);
        boolean check = accountServiceClient.checkAccountForLoan(accountId, userId);
        if (check) {
            if (loanService.turnAutodebtOn(loanId, accountId))
                return ResponseEntity.ok(Map.of("status", "success", "message", "Autodebt on successfully"));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{loanId}/autodebt")
    public ResponseEntity<Map<String, String>> disableAutodebt(@PathVariable String loanId) {
        if (loanService.turnAutodebtOff(loanId))
            return ResponseEntity.ok(Map.of("status", "success", "message", "Autodebt off"));
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{loanId}/replenish")
    public ResponseEntity<Map<String, String>> replenishLoan(@PathVariable String loanId, @RequestBody PurchaseDto dto) {
        if (loanService.purchaseLoan(loanId, dto, false)){
            return ResponseEntity.ok(Map.of("status", "success", "message", "Loan purchased successfully"));
        }
        return ResponseEntity.badRequest().build();
    }

}
