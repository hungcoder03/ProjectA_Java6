package com.adc.project_a.controller;

import com.adc.project_a.entity.User;
import com.adc.project_a.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam(required = false) String fullName,
                                      RedirectAttributes redirectAttributes,
                                      Model model) {
        try {
            userService.registerUser(username, email, password, fullName);
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/auth/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("fullName", fullName);
            return "register";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Đã có lỗi xảy ra trong quá trình đăng ký. Vui lòng thử lại.");
            e.printStackTrace();
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String usernameOrEmail,
                               @RequestParam String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        Optional<User> userOptional = userService.loginUser(usernameOrEmail, password);

        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Tên đăng nhập/Email hoặc mật khẩu không chính xác.");
            model.addAttribute("usernameOrEmail", usernameOrEmail);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.removeAttribute("loggedInUser");
        redirectAttributes.addFlashAttribute("successMessage", "Bạn đã đăng xuất thành công.");
        return "redirect:/";
    }
}
