package am.itspace.overnight.controller.admin;

import am.itspace.overnight.entity.User;
import am.itspace.overnight.security.CurrentUser;
import am.itspace.overnight.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;

    @GetMapping("/adminPage")
    public String adminpage() {
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