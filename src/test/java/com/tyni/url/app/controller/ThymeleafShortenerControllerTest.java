package com.tyni.url.app.controller;

import com.tyni.url.app.dto.UrlDto;
import com.tyni.url.app.services.ServiceUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThymeleafShortenerControllerTest {

    @Mock
    private ServiceUrl serviceUrl;

    @Mock
    private Model model;

    @InjectMocks
    private ThymeleafShortenerController controller;

    @Test
    void testIndex() {
        String result = controller.index(model);

        assertEquals("index", result);
        verify(model).addAttribute("mensagem", "Painel de Controle do Projeto");
    }

    @Test
    void testCreateShortUrl() {
        UrlDto urlDto = new UrlDto("http://localhost:8081/tu/abc12", 1234567890L);

        when(serviceUrl.createShortUrl(any())).thenReturn(urlDto);

        String result = controller.createShortUrl("https://www.google.com", model);

        assertEquals("index", result);
        verify(model).addAttribute("mensagem", "URL Encurtada com sucesso!");
        verify(model).addAttribute("urlGerada", "http://localhost:8081/tu/abc12");
        verify(model).addAttribute(eq("dataCriacao"), any(String.class));
    }
}