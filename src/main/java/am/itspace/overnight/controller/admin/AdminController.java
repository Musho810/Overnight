package am.itspace.overnight.controller.admin;

import am.itspace.overnight.entity.User;
import am.itspace.overnight.security.CurrentUser;
import am.itspace.overnight.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private  CurrentUser currentUser;

    @GetMapping("/adminPage")
    public String adminpage( @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             ModelMap modelMap) {

            int currentPage = page.orElse(1);
            int pageSize = size.orElse(5);
            Page<User> users = adminService.findUsersByUserRole("SELLER",
                    PageRequest.of(currentPage - 1, pageSize));
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
    public String users() {
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
    public String updateProfile(@ModelAttribute User user) {
        adminService.update(user);
        return "redirect:/";
    }

}