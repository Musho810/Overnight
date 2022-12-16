package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Room;
import am.itspace.overnight.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;



    @GetMapping("/resultRoom/{id}")
    public String roomSinglePage(@PathVariable("id") int id, ModelMap modelMap){
        Optional<Room> byId = roomService.findById(id);
        if(byId.isEmpty()){
            return "redirect:/searchResult";
        }

        modelMap.addAttribute("rooms", byId.get());
        return "roomBook";
    }

}
