package patterns.users.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.users.data.dto.response.PositionDto;
import patterns.users.data.entity.PositionEntity;
import patterns.users.data.repository.PositionRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionEntity getPositionById(String id) {
        return positionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Position not found"));
    }

    public List<PositionDto> getAllPositions() {
        Iterable<PositionEntity> positions = positionRepository.findAll();
        return StreamSupport.stream(positions.spliterator(), false)
                .map(this::hydratePositionDto)
                .collect(Collectors.toList());
    }

    public PositionDto getPosition(String positionId) {
        return hydratePositionDto(getPositionById(positionId));
    }

    private PositionDto hydratePositionDto(PositionEntity position) {
        PositionDto dto = new PositionDto();
        dto.setId(position.getId());
        dto.setName(position.getName());
        dto.setDescription(position.getDescription());
        return dto;
    }

}
