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

    public Optional<CityVillage> getById(int cityVilageId) {
        return cityVillageRepository.findById(cityVilageId);
    }

    public void updateCityVillage(int cityVilageId, String cityVilageName, Region region) {
        Optional<CityVillage> cityVilageById = cityVillageRepository.findById(cityVilageId);
        if (cityVilageById.isPresent()) {
            CityVillage cityVillage = cityVilageById.get();
            cityVillage.setName(cityVilageName);
            cityVillage.setRegion(region);
        }
    }

    public void deleteById(int cityVilageId) {
        cityVillageRepository.deleteById(cityVilageId);
    }
}
