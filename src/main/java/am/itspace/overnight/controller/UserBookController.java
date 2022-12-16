package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Attribute;
import am.itspace.overnight.entity.Region;
import am.itspace.overnight.entity.Room;
import am.itspace.overnight.service.AttributeService;
import am.itspace.overnight.service.AttributeValueService;
import am.itspace.overnight.service.RegionService;
import am.itspace.overnight.service.UserBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class UserBookController {

    private final RegionService regionService;

    private final UserBookService userBookService;

    private final AttributeService attributeService;

    private final AttributeValueService attributeValueService;


    @GetMapping("/search-result")
    public String searchResult(
            @RequestParam(value = "region") int regionId,
            @RequestParam(value = "dateFrom") String startDate,
            @RequestParam(value = "dateTo") String endDate,
            @RequestParam(value = "guestsAdult") int guestsAdult,
            @RequestParam(value = "guestsChildren") int guestsChildren,
            @PageableDefault(size = 6) Pageable pageable,
            ModelMap modelMap) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date start = simpleDateFormat.parse(startDate);
        Date end = simpleDateFormat.parse(endDate);
        if (regionId == 0) {
            Page<Room> roomsAll = userBookService.findAll(pageable);
            modelMap.addAttribute("resultRooms", roomsAll);
            int totalPages = roomsAll.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                modelMap.addAttribute("pageNumbers", pageNumbers);
            }
        } else {
            Page<Room> rooms = userBookService.findProductSearchResult(pageable, regionId, start, end, guestsAdult, guestsChildren);
            modelMap.addAttribute("resultRooms", rooms);
            int totalPages = rooms.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                modelMap.addAttribute("pageNumbers", pageNumbers);
            }
        }
        List<Region> regions = regionService.findAll();
        modelMap.addAttribute("regions", regions);

        List<Attribute> attributes = attributeService.findAll();
        modelMap.addAttribute("attributes", attributes);
        return "searchResult";
    }


//            @GetMapping("/search-result/attribute")
//    public String attributeFilter(
//            @RequestParam(value = "attribute")String attribute ,ModelMap modelMap){
//        List<AttributeValue> attributes = attributeValueService.findAttributeSearchResult(attribute);
//        modelMap.addAttribute("attributes", attribute);
//        return "searchResult";
//            }

}

