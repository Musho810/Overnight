package am.itspace.overnight.service;

import am.itspace.overnight.entity.AttributeValue;
import am.itspace.overnight.repository.AttributeValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeValueService {

    private final AttributeValueRepository attributeValueRepository;

//    public List<AttributeValue> findAttributeSearchResult(String attribute) {
//        return attributeValueRepository.findAttributeSearchResult(attribute);
//    }
}
