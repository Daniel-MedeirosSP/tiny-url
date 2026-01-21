package com.tyni.url.app.controller;

import com.tyni.url.app.dto.UrlDto;
import com.tyni.url.app.services.ServiceUrl;
import com.tyni.url.app.to.UrlTo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ThymeleafShortenerController {

    private final ServiceUrl serviceUrl;

    public ThymeleafShortenerController(ServiceUrl serviceUrl){
        this.serviceUrl = serviceUrl;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mensagem", "Painel de Controle do Projeto");
        return "index";
    }

    @PostMapping("/shorten")
    public String createShortUrl(@RequestParam String originalUrl, Model model) {
        UrlTo urlTo = new UrlTo();
        urlTo.setOriginalUrl(originalUrl);
        UrlDto result = serviceUrl.createShortUrl(urlTo);
        model.addAttribute("mensagem", "URL Encurtada com sucesso!");
        model.addAttribute("urlGerada", result.getTinyUrl()); // Esse nome deve bater com o th:if
        model.addAttribute("dataCriacao", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        return "index";
    }

}