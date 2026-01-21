package com.tyni.url.app.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlDtoTest {

    @Test
    void testUrlDtoGettersAndSetters() {
        UrlDto urlDto = new UrlDto();
        
        urlDto.setTinyUrl("abc12");
        
        assertEquals("abc12", urlDto.getTinyUrl());
    }
}