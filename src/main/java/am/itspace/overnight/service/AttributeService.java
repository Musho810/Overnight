package am.itspace.overnight.service;

import am.itspace.overnight.entity.Attribute;
import am.itspace.overnight.entity.AttributeValue;
import am.itspace.overnight.repository.AttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {

    private final AttributeRepository attributeRepository;

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

}
