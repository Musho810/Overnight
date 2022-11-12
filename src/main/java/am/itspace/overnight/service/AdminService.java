package am.itspace.overnight.service;

import am.itspace.overnight.entity.*;
import am.itspace.overnight.repository.AttributeRepository;
import am.itspace.overnight.repository.RegionRepository;
import am.itspace.overnight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final RegionRepository regionRepository;
    private final AttributeRepository attributeRepository;
    private final UserRepository userRepository;

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


    public void save(User user) {
        userRepository.save(user);
    }


    public void update(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {
            byId.get().setName(user.getName());
            byId.get().setSurname(user.getSurname());
            byId.get().setEmail(user.getEmail());
            byId.get().setPhoneNumber(user.getPhoneNumber());
            byId.get().setStatus(user.getStatus());


            userRepository.save(byId.get());
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    public Page<User> findUsersByUserRole(RoleUser seller, Pageable pageable, StatusSeller status) {
        if (status == null) {
            return userRepository.findUserByRole(seller, pageable);
        }
        return userRepository.findUserByRoleAndStatus(seller, status, pageable);
    }

    public void edit(int id, StatusSeller status) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            user.setStatus(status);
            userRepository.save(user);
        }
    }
}