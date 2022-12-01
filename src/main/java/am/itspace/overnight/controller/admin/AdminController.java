package am.itspace.overnight.controller.admin;

import am.itspace.overnight.entity.RoleUser;
import am.itspace.overnight.entity.StatusSeller;
import am.itspace.overnight.entity.User;
import am.itspace.overnight.entity.UserBook;
import am.itspace.overnight.security.CurrentUser;
import am.itspace.overnight.service.AdminService;
import lombok.RequiredArgsConstructor;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    @Value("${overnight.images.folder}")
    private String folderPath;


    @GetMapping("/adminPage")
    public String adminPage(@PageableDefault(size = 5) Pageable pageable,
                            @RequestParam(value = "status", required = false) StatusSeller status,
                            ModelMap modelMap
    ) {

        Page<User> users = adminService.findUsersByUserRole(RoleUser.SELLER,
                pageable, status);
        int totalPages = users.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("users", users);

        return "admin/adminPage";
    }

    @GetMapping("/user")
    public String orders(@PageableDefault(size = 5) Pageable pageable,
                         @RequestParam(value = "startDate", required = false) Date startDate,
                         @RequestParam(value = "endDate", required = false) Date endDate,
                         @RequestParam(value = "keyword", required = false) String keyword,
                         ModelMap modelMap) {

        Page<UserBook> orders = adminService.findUserBookAll(pageable, startDate, endDate, keyword);
        int totalPages = orders.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("orders", orders);
        return "admin/adminPageUser";
    }

    @GetMapping("/attributes")
    public String adminPageAttribute() {
        return "admin/adminPageAttribute";
    }

    @GetMapping("/profileSettings")
    public String profSett() {
        return "/admin/adminProfilePage";
    }

    @PostMapping("/attributes")
    public String edit(@ModelAttribute CurrentUser currentUser) {
        return "redirect:/adminPage";
    }

    @PostMapping("/password/change")

    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,

                                 @AuthenticationPrincipal CurrentUser currentUser,
                                 ModelMap modelMap) {
        Optional<User> userByEmail = adminService.getUserByEmail(currentUser.getUsername());
        if (userByEmail.isPresent()) {
            if (confirmPassword.equals(newPassword) && passwordEncoder.matches(oldPassword, userByEmail.get().getPassword())) {
                userByEmail.get().setPassword(passwordEncoder.encode(newPassword));
                adminService.save(userByEmail.get());
                return "redirect:/";
            }
        }
        modelMap.addAttribute("errorMessage", "DOES NOT UPDATE");
        return "/admin/adminProfilePage";
    }

    @PostMapping("/admin/update")
    public String updateProfile(@ModelAttribute User user){
        adminService.update(user);
        return "redirect:/";
    }
    @PostMapping("/image/add")
    public String addImage(@AuthenticationPrincipal CurrentUser currentUser,
                           @RequestParam(value = "userImage", required = false) MultipartFile file) throws IOException {
        adminService.image(currentUser,file);
        return "redirect:/user";
    }

    @GetMapping(value = "/user/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }
    @GetMapping("/status/edit")
    public String editStatus(@RequestParam("id") int id,
                             @RequestParam(value = "statusUser", required = false) StatusSeller status) {
        adminService.edit(id, status);
        return "redirect:/adminPage";
    }

}