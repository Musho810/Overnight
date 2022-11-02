package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Region;
import am.itspace.overnight.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/region/add")
    public String addRegion(@ModelAttribute Region region) {
        regionService.addRegion(region);
        return "redirect:/region";
    }
    @GetMapping("/region")
    public String getAllRegions(ModelMap modelMap) {
        List<Region> regions = regionService.getAll();
        modelMap.addAttribute("regions", regions);
        return "/admin/adminPageRegion";
    }
    @GetMapping("/region/id")
    public String getRegionById(@RequestParam int regionId, ModelMap modelMap) {
        Optional<Region> region=regionService.getById(regionId);
        modelMap.addAttribute("region", region);
        return "/regions";
    }
    @PostMapping("/region/update")
    public String updateRegion (@RequestParam("regionId") int regionId,
                                @RequestParam("regionName") String regionName ){
        regionService.updateRegion(regionId,regionName);
        return "redirect:/regions";
    }
    @GetMapping("/region/delete")
    public String deleteRegion(@RequestParam("regionId") int regionId) {
        regionService.deleteById(regionId);
        return "redirect:/region";
    }
}
