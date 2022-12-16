package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Region;
import am.itspace.overnight.entity.Room;
import am.itspace.overnight.service.ProductService;
import am.itspace.overnight.service.RegionService;
import am.itspace.overnight.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RegionService regionService;

    private final ProductService productService;

    private final RoomService roomService;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        List<Region> regions = regionService.findAll();
        modelMap.addAttribute("regions", regions);
        List<Room> last2Product = roomService.findRoomByRating();
        modelMap.addAttribute("ratings", last2Product);
        return "index";
    }

}