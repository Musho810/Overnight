package am.itspace.overnight.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttributeValueController {

    @GetMapping("attribute-value")
    public String attributeValue(){
        return "attribute-value";
    }
}
