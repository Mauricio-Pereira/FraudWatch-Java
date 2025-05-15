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
import org.springframework.web.bind.annotation.RequestParam;
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
        return "registrationForm";
    }


    // Processa o formulário unificado e finaliza o cadastro
    @PostMapping("/final-registration")
    public String finalizeRegistration(
            @RequestParam("nome") String nome,
            @RequestParam("sobrenome") String sobrenome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("cpf") String cpf,
            @RequestParam("dataNascimento") String dataNascimentoStr,
            @RequestParam("telefone") String telefone,
            @RequestParam("tipoUsuarioid") Long tipoUsuarioid,
            @RequestParam("endereco.cep") String cep,
            @RequestParam("endereco.logradouro") String logradouro,
            @RequestParam("endereco.bairro") String bairro,
            @RequestParam("endereco.cidade") String cidade,
            @RequestParam("endereco.estado") String estado,
            @RequestParam("endereco.regiao") String regiao,
            @RequestParam("endereco.numero") String numero,
            @RequestParam(value = "endereco.complemento", required = false) String complemento,
            RedirectAttributes redirectAttributes
    ) {
        // Validação básica: se o CEP (campo obrigatório do endereço) estiver vazio, rejeita a submissão
        if (cep == null || cep.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "CEP é obrigatório.");
            return "redirect:/create-account";
        }
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
        EnderecoRequest endereco = new EnderecoRequest(cep, logradouro, bairro, cidade, estado, regiao, complemento, numero);
        UsuarioRequest usuarioRequest = new UsuarioRequest(nome, sobrenome, email, senha, cpf, dataNascimento, telefone, tipoUsuarioid, endereco);

        // Armazena o objeto no flash para exibição na página de confirmação
        redirectAttributes.addFlashAttribute("usuarioRequest", usuarioRequest);
        return "redirect:/create-account/final-registration";
    }


    @GetMapping("/final-registration")
    public String showFinalRegistration(Model model) {
        if (!model.containsAttribute("usuarioRequest")) {
            return "redirect:/create-account";
        }
        return "finalRegistration";
    }

    // Processa o formulário unificado e finaliza o cadastro
    @PostMapping("/register")
    public String register(
            @RequestParam("nome") String nome,
            @RequestParam("sobrenome") String sobrenome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("cpf") String cpf,
            @RequestParam("dataNascimento") String dataNascimentoStr,
            @RequestParam("telefone") String telefone,
            @RequestParam("tipoUsuarioid") Long tipoUsuarioid,
            @RequestParam("endereco.cep") String cep,
            @RequestParam("endereco.logradouro") String logradouro,
            @RequestParam("endereco.bairro") String bairro,
            @RequestParam("endereco.cidade") String cidade,
            @RequestParam("endereco.estado") String estado,
            @RequestParam("endereco.regiao") String regiao,
            @RequestParam("endereco.numero") String numero,
            @RequestParam(value = "endereco.complemento", required = false) String complemento,
            RedirectAttributes redirectAttributes
    ) {
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
        EnderecoRequest endereco = new EnderecoRequest(cep, logradouro, bairro, cidade, estado, regiao, complemento, numero);
        UsuarioRequest usuarioRequest = new UsuarioRequest(nome, sobrenome, email, senha, cpf, dataNascimento, telefone, tipoUsuarioid, endereco);
        try {
            Usuario usuario = usuarioService.createUsuarioWithProcedure(usuarioRequest);
            System.out.println(usuario);
            redirectAttributes.addFlashAttribute("popupMessage", "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popupMessage", "Erro ao cadastrar usuário. Tente novamente.");
        }
        return "redirect:/create-account/popup";
    }

    @GetMapping("/popup")
    public String showPopup() {
        return "popup"; // Certifique-se de que exista um arquivo popup.html em resources/templates
    }


}
