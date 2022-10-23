package am.itspace.overnight.service;

import am.itspace.overnight.entity.Attribute;
import am.itspace.overnight.entity.Region;
import am.itspace.overnight.repository.AttributeRepository;
import am.itspace.overnight.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final RegionRepository regionRepository;
    private final AttributeRepository attributeRepository;

    public List<Region> findAllRegion() {
        return regionRepository.findAll();
    }

    public void regionSave(Region region) {
        regionRepository.save(region);

    }

    public void deleteRegionById(int id) {
        regionRepository.deleteById(id);
    }

    public void attributeSave(Attribute attribute) {
        attributeRepository.save(attribute);
    }

    public List<Attribute> findAllAttribute() {
        return attributeRepository.findAll();
    }

    public void deleteAttributeById(int id) {
        attributeRepository.deleteById(id);
    }
}