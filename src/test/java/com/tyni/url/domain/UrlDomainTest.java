package com.tyni.url.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UrlDomainTest {

    @Test
    void testUrlDomainConstructorAndGetters() {
        Date createdAt = new Date();
        Date expiredAt = new Date();
        
        UrlDomain urlDomain = new UrlDomain("1", "https://www.google.com", "abc12", createdAt, expiredAt);
        
        assertEquals("1", urlDomain.getId());
        assertEquals("https://www.google.com", urlDomain.getOriginalUrl());
        assertEquals("abc12", urlDomain.getTinyUrl());
        assertEquals(createdAt, urlDomain.getCreatedAt());
        assertEquals(expiredAt, urlDomain.getExpiredAt());
    }

    @Test
    void testUrlDomainSetters() {
        UrlDomain urlDomain = new UrlDomain();
        Date createdAt = new Date();
        Date expiredAt = new Date();
        
        urlDomain.setId("2");
        urlDomain.setOriginalUrl("https://www.example.com");
        urlDomain.setTinyUrl("xyz34");
        urlDomain.setCreatedAt(createdAt);
        urlDomain.setExpiredAt(expiredAt);
        
        assertEquals("2", urlDomain.getId());
        assertEquals("https://www.example.com", urlDomain.getOriginalUrl());
        assertEquals("xyz34", urlDomain.getTinyUrl());
        assertEquals(createdAt, urlDomain.getCreatedAt());
        assertEquals(expiredAt, urlDomain.getExpiredAt());
    }
}