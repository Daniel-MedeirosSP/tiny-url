package com.tyni.url.app.services;

import com.tyni.url.app.dto.UrlDto;
import com.tyni.url.app.repositories.ServiceRepository;
import com.tyni.url.app.to.UrlTo;
import com.tyni.url.domain.UrlDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceUrlTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceUrl serviceUrl;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(serviceUrl, "tamanho", 5);
        ReflectionTestUtils.setField(serviceUrl, "baseUrl", "http://localhost:8081/rd/");
        ReflectionTestUtils.setField(serviceUrl, "hashchar", "abcdefghijklmnopqrstuvwxyz");
        ReflectionTestUtils.setField(serviceUrl, "expiredTime", 1L);
    }

    @Test
    void shouldCreateShortUrl() {
        UrlTo to = new UrlTo();
        to.setOriginalUrl("https://google.com");

        UrlDomain domain = new UrlDomain();
        domain.setTinyUrl("abcde");
        domain.setExpiredAt(new Date(System.currentTimeMillis() + 3600000));

        when(serviceRepository.save(any(UrlDomain.class))).thenReturn(domain);

        UrlDto result = serviceUrl.createShortUrl(to);

        assertNotNull(result);
        assertTrue(result.getTinyUrl().contains("abcde"));
        verify(serviceRepository).save(any(UrlDomain.class));
    }

    @Test
    void shouldReturnOriginalUrlWhenNotExpired() {
        UrlDomain domain = new UrlDomain();
        domain.setOriginalUrl("https://google.com");
        domain.setExpiredAt(new Date(System.currentTimeMillis() + 3600000));

        when(serviceRepository.findFirstByTinyUrl("abcde")).thenReturn(domain);

        String result = serviceUrl.getOriginalUrl("abcde");

        assertEquals("https://google.com", result);
    }

    @Test
    void shouldThrowExceptionAndDeleteWhenUrlIsExpired() {
        UrlDomain domain = new UrlDomain();
        domain.setExpiredAt(new Date(System.currentTimeMillis() - 3600000));

        when(serviceRepository.findFirstByTinyUrl("expired")).thenReturn(domain);

        assertThrows(RuntimeException.class, () -> serviceUrl.getOriginalUrl("expired"));
        verify(serviceRepository).delete(domain);
    }
}