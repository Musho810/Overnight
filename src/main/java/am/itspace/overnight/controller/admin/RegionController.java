package am.itspace.overnight.controller.admin;

import am.itspace.overnight.entity.Region;
import am.itspace.overnight.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegionController {
    private final AdminService adminService;


    @GetMapping("/region")
    public String region(ModelMap modelMap) {
        List<Region> allRegion = adminService.findAllRegion();
        modelMap.addAttribute("allRegion", allRegion);
        return "admin/adminPageRegion";
    }

    @PostMapping("/region/add")
    public String addRegion(@ModelAttribute Region region) {
        adminService.regionSave(region);
        return "redirect:/region";
    }

    @GetMapping("/region/delete")
    public String delete(@RequestParam("id") int id) {
        adminService.deleteRegionById(id);
        return "redirect:/region";

    }


}
