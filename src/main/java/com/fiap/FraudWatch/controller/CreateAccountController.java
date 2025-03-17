package com.fiap.FraudWatch.controller;

import com.fiap.FraudWatch.dto.accountDto.AddressDataDTO;
import com.fiap.FraudWatch.dto.accountDto.PersonalAccountDTO;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioRequest;
import com.fiap.FraudWatch.model.Usuario;
import com.fiap.FraudWatch.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/create-account")
@SessionAttributes({"personalAccountData", "addressData"})
public class CreateAccountController {

    @Autowired
    private UsuarioService usuarioService;

    // Exibe o formulário unificado de cadastro
    @GetMapping
    public String showRegistrationForm(Model model) {
        // Cria um objeto com valores padrão (ajuste conforme necessário)
        UsuarioRequest usuarioRequest = new UsuarioRequest(
                "", // nome
                "", // sobrenome
                "", // email
                "", // senha
                "", // cpf
                LocalDate.now(), // dataNascimento (ou null, se preferir)
                "", // telefone
                1L, // tipoUsuarioid (fixo como 1)
                new EnderecoRequest("", "", "", "", "", "", "", "")
                // endereço com valores vazios
        );
        model.addAttribute("usuarioRequest", usuarioRequest);
        System.out.println(usuarioRequest);
        return "registrationForm";
    }


    // Processa o formulário unificado e finaliza o cadastro
    @PostMapping("/final-registration")
    public String finalizeRegistration(
            @Valid @ModelAttribute UsuarioRequest usuarioRequest,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "registrationForm";
        }

        // Se não houver erros, adiciona o objeto usuarioRequest como atributo flash
        redirectAttributes.addFlashAttribute("usuarioRequest", usuarioRequest);

        return "redirect:/create-account/final-registration";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowNestedPaths(false);
    }

    @GetMapping("/final-registration")
    public String showFinalRegistration(Model model) {
        // Se o objeto não estiver presente, redireciona para o formulário
        if (!model.containsAttribute("usuarioRequest")) {
            return "redirect:/create-account";
        }

        // Obtém o objeto usuarioRequest do atributo flash
        UsuarioRequest usuarioRequest =
                (UsuarioRequest) model.getAttribute("usuarioRequest");
        System.out.println(usuarioRequest);
        return "finalRegistration";
    }

    // Processa o formulário unificado e finaliza o cadastro
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute UsuarioRequest usuarioRequest,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "registrationForm";
        }
        //usa o service para salvar o usuario
        try {
            Usuario usuario =
                    usuarioService.createUsuarioWithProcedure(usuarioRequest);
            System.out.println(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            // Se não houver erros, adiciona o objeto usuarioRequest como atributo flash
        }
        return "redirect:/";
    }
}
