package patterns.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patterns.core.data.document.OperationDocument;
import patterns.core.data.enums.OperationType;
import patterns.core.service.OperationService;
import patterns.core.service.communication.UserServiceClient;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;
    private final UserServiceClient userServiceClient;

    @PutMapping("/autodebt/{accountId}")
    public ResponseEntity<Void> autodebt(@PathVariable String accountId, @RequestParam int sum) {
        operationService.logAutodebtOperation(accountId, sum);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/my/{accountId}")
    public ResponseEntity<List<OperationDocument>> getMyOperations(@RequestHeader("Authorization") String authHeader,
                                                 @PathVariable String accountId) {
        String userId = userServiceClient.getUserId(authHeader);
        return ResponseEntity.ok(operationService.getOperationsByAccountId(accountId, userId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<OperationDocument>> getOperations(@PathVariable String accountId) {
        return ResponseEntity.ok(operationService.getOperationsByAccountId(accountId, null));
    }
}
