package com.fiap.FraudWatch.controller;

import com.fiap.FraudWatch.model.Usuario;
import com.fiap.FraudWatch.repository.UsuarioRepository;
import com.fiap.FraudWatch.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("senha") String senha,
                        RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (!usuarioOptional.isPresent() || !usuarioOptional.get().getSenha().equals(senha)) {
            redirectAttributes.addFlashAttribute("error", "Usuário ou senha inválidos.");
            return "redirect:/";
        }
        Usuario usuario = usuarioOptional.get();
        if (usuario.getEmail().equals("admin@admin.com") && usuario.getSenha().equals("@Dmin1234")) {
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuário não tem permissão para acessar o sistema.");
            return "redirect:/";
        }

    }

}
