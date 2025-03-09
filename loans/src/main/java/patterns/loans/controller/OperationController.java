package patterns.loans.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import patterns.loans.data.document.OperationDocument;
import patterns.loans.service.OperationService;
import patterns.loans.service.communication.UserServiceClient;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;
    private final UserServiceClient userServiceClient;


    @GetMapping("/my/{loanId}")
    public List<OperationDocument> getMyOperations(@RequestHeader("Authorization") String authHeader,
                                                   @PathVariable String loanId) {
        String userId = userServiceClient.getUserId(authHeader);
        return operationService.getOperationsByLoanId(loanId, userId);
    }

    @GetMapping("/{loanId}")
    public List<OperationDocument> getOperations(@PathVariable String loanId) {
        return operationService.getOperationsByLoanId(loanId, null);
    }
}
