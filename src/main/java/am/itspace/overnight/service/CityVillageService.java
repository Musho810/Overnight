package am.itspace.overnight.service;

import am.itspace.overnight.entity.CityVillage;
import am.itspace.overnight.entity.Region;
import am.itspace.overnight.repository.CityVillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityVillageService {
    private final CityVillageRepository cityVillageRepository;

    public void addCityVillage(CityVillage cityVillage) {
        cityVillageRepository.save(cityVillage);
    }

    public List<CityVillage> getAll() {
        List<CityVillage> cityVillageList = cityVillageRepository.findAll();
        return cityVillageList;
    }

    public Optional<CityVillage> getById(int cityVillageId) {
        return cityVillageRepository.findById(cityVillageId);
    }

    public void updateCityVillage(int cityVillageId, String cityVillageName, Region region) {
        Optional<CityVillage> cityVillageById = cityVillageRepository.findById(cityVillageId);
        if (cityVillageById.isPresent()) {
            CityVillage cityVillage = cityVillageById.get();
            cityVillage.setName(cityVillageName);
            cityVillage.setRegion(region);
        }
    }

    public void deleteById(int cityVillageId) {
        cityVillageRepository.deleteById(cityVillageId);
    }
}
