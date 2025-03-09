package patterns.loans.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.loans.data.dto.request.CreateTariffDto;
import patterns.loans.data.dto.response.TariffDto;
import patterns.loans.data.dto.response.TariffSmallDto;
import patterns.loans.data.entity.TariffEntity;
import patterns.loans.data.repository.TariffRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TariffService {
    private final TariffRepository tariffRepository;

    public TariffEntity getTariffById(String id) {
        return tariffRepository.findById(id).orElse(null);
    }

    public void createTariff(CreateTariffDto tariffDto, String employeeId) {
        tariffRepository.save(hydrateTariffEntity(tariffDto, employeeId));
    }

    public void deleteTariff(String tariffId) {
        tariffRepository.deleteById(tariffId);
    }

    public List<TariffDto> getAllTariffs() {
        return StreamSupport.stream(tariffRepository.findAll().spliterator(), false)
                .map(this::hydrateTariffDto)
                .collect(Collectors.toList());
    }

    public TariffSmallDto hydrateTariffSmallDto(TariffEntity tariffEntity) {
        TariffSmallDto tariffSmallDto = new TariffSmallDto();
        tariffSmallDto.setName(tariffEntity.getName());
        tariffSmallDto.setProcent(tariffEntity.getProcent());
        return tariffSmallDto;
    }

    private TariffDto hydrateTariffDto(TariffEntity tariff) {
        TariffDto response = new TariffDto();
        response.setId(tariff.getId());
        response.setEmployeeId(tariff.getUserId());
        response.setName(tariff.getName());
        response.setProcent(tariff.getProcent());
        return response;
    }

    private TariffEntity hydrateTariffEntity(CreateTariffDto tariffDto, String employeeId) {
        TariffEntity tariff = new TariffEntity();
        tariff.setId(String.valueOf(UUID.randomUUID()));;
        tariff.setUserId(employeeId);
        tariff.setName(tariffDto.getName());
        tariff.setProcent(tariffDto.getProcent());
        return tariff;
    }
}
