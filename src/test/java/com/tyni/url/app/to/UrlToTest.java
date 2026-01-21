package com.tyni.url.app.to;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlToTest {

    @Test
    void testUrlToGettersAndSetters() {
        UrlTo urlTo = new UrlTo();
        
        urlTo.setOriginalUrl("https://www.google.com");
        
        assertEquals("https://www.google.com", urlTo.getOriginalUrl());
    }
}