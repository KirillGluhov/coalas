package patterns.loans.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patterns.loans.data.dto.request.CreateTariffDto;
import patterns.loans.data.dto.response.TariffDto;
import patterns.loans.service.TariffService;
import patterns.loans.service.communication.UserServiceClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tariffs")
@RequiredArgsConstructor
public class TariffController {

    private final TariffService tariffService;
    private final UserServiceClient userServiceClient;

    @GetMapping
    public ResponseEntity<List<TariffDto>> getAllTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariffs());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTariff(@RequestHeader("Authorization") String authHeader,
            @RequestBody CreateTariffDto tariffDto) {
        String employeeId = userServiceClient.getUserId(authHeader);
        tariffService.createTariff(tariffDto, employeeId);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Tariff created successfully"));
    }

    @DeleteMapping("/{tariffId}")
    public ResponseEntity<Map<String, String>> deleteTariff(@PathVariable String tariffId) {
        tariffService.deleteTariff(tariffId);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Tariff deleted successfully"));
    }
}
