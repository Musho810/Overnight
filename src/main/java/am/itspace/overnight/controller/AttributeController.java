package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Attribute;
import am.itspace.overnight.entity.StatusSeller;
import am.itspace.overnight.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttributeController {

    private final AdminService adminService;

    @GetMapping("/attribute")
    public String attribute(@RequestParam(value = "keyword", required = false) String keyword,
                            ModelMap modelMap) {
        List<Attribute> allAttribute = adminService.findAllAttribute(keyword);
        modelMap.addAttribute("allAttribute",allAttribute);

        return "admin/adminPageAttribute";
    }

    @PostMapping("/attribute/add")
    public String add(@ModelAttribute Attribute attribute) {
        adminService.attributeSave(attribute);
        return "redirect:/attribute";
    }

    @GetMapping("/attribute/{id}")
    public String delete(@PathVariable int id) {
         adminService.deleteAttributeById(id);
        return "redirect:/attribute";
    }
}
