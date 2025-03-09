package patterns.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patterns.users.data.dto.response.PositionDto;
import patterns.users.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }

    @GetMapping("/{positionId}")
    public ResponseEntity<PositionDto> getPosition(@PathVariable String positionId) {
        return ResponseEntity.ok(positionService.getPosition(positionId));
    }
}