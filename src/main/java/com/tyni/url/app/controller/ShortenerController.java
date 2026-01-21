package com.tyni.url.app.controller;

import com.tyni.url.app.dto.UrlDto;
import com.tyni.url.app.services.ServiceUrl;
import com.tyni.url.app.to.UrlTo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortenerController {


    private final ServiceUrl serviceUrl;

    public ShortenerController(ServiceUrl serviceUrl){
        this.serviceUrl = serviceUrl;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<UrlDto> createShortUrl(@RequestBody UrlTo originalUrl) {
        return ResponseEntity.ok(serviceUrl.createShortUrl(originalUrl));
    }
    
    @GetMapping("/rd/{tinyUrl}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String tinyUrl) {
        var url = serviceUrl.getOriginalUrl(tinyUrl);
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
