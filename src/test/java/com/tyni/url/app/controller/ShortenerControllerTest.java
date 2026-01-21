package com.tyni.url.app.controller;

import com.tyni.url.app.dto.UrlDto;
import com.tyni.url.app.services.ServiceUrl;
import com.tyni.url.app.to.UrlTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShortenerControllerTest {

    @Mock
    private ServiceUrl serviceUrl;

    @InjectMocks
    private ShortenerController controller;

    @Test
    @DisplayName("Deve criar uma URL curta com sucesso")
    void testCreateShortUrl() {
        UrlTo urlTo = new UrlTo();
        urlTo.setOriginalUrl("https://www.google.com");

        UrlDto expectedDto = new UrlDto("http://localhost:8081/rd/abc12", 1234567890L);

        when(serviceUrl.createShortUrl(any(UrlTo.class))).thenReturn(expectedDto);

        ResponseEntity<UrlDto> response = controller.createShortUrl(urlTo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
        verify(serviceUrl, times(1)).createShortUrl(urlTo);
    }

    @Test
    @DisplayName("Deve redirecionar mantendo o protocolo HTTPS")
    void testGetOriginalUrlWithHttps() {

        String tinyUrl = "abc12";
        String originalUrl = "https://www.google.com";
        when(serviceUrl.getOriginalUrl(tinyUrl)).thenReturn(originalUrl);

        ResponseEntity<Void> response = controller.getOriginalUrl(tinyUrl);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(originalUrl, response.getHeaders().getLocation().toString());
    }

    @Test
    @DisplayName("Deve redirecionar adicionando o protocolo HTTP quando ausente")
    void testGetOriginalUrlWithoutProtocol() {

        String tinyUrl = "abc12";
        String originalUrlWithoutProtocol = "www.google.com";
        String expectedRedirect = "http://www.google.com";

        when(serviceUrl.getOriginalUrl(tinyUrl)).thenReturn(originalUrlWithoutProtocol);

        ResponseEntity<Void> response = controller.getOriginalUrl(tinyUrl);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(expectedRedirect, response.getHeaders().getLocation().toString());
    }

    @Test
    @DisplayName("Deve redirecionar mantendo o protocolo HTTP se já presente")
    void testGetOriginalUrlWithHttp() {
        String tinyUrl = "abc12";
        String originalUrl = "http://www.google.com";
        when(serviceUrl.getOriginalUrl(tinyUrl)).thenReturn(originalUrl);

        ResponseEntity<Void> response = controller.getOriginalUrl(tinyUrl);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(originalUrl, response.getHeaders().getLocation().toString());
    }
}