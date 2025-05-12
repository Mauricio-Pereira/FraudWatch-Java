package com.fiap.FraudWatch.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.Locale;

@Controller
public class LocaleController {
        @GetMapping("/lang")
        public String changeLang(@RequestParam String lang, HttpServletRequest request) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver != null) {
                localeResolver.setLocale(request, null, new Locale(lang));
            }
            return "redirect:/";
        }
}
