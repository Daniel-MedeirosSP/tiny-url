package com.tyni.url.app.services;

import com.tyni.url.app.dto.UrlDto;
import com.tyni.url.app.repositories.ServiceRepository;
import com.tyni.url.app.to.UrlTo;
import com.tyni.url.domain.UrlDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
public class ServiceUrl {

    @Value("${app.expired}")
    private Long expiredTime;

    @Value("${app.char}")
    private String hashchar;

    @Value("${app.tamanho}")
    private Integer tamanho;

    @Autowired
    private ServiceRepository serviceRepository;

    public UrlDto createShortUrl(UrlTo urlTo) {
        var urlDomain = new UrlDomain(null, urlTo.getOriginalUrl(), GenerateNewUrl(), new Date(), TimeExpired());
        urlDomain = serviceRepository.save(urlDomain);


        return new UrlDto(urlDomain.getTinyUrl(), urlDomain.getExpiredAt().getTime());
    }

    public Date TimeExpired() {
        return Date.from(LocalDateTime.now()
                .plusHours(expiredTime)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public String GenerateNewUrl() {

        Random random = new Random();
        StringBuilder result = new StringBuilder(tamanho);

        for (int i = 0; i < 5; i++) {
            result.append(hashchar.charAt(random.nextInt(hashchar.length())));
        }

        return result.toString();
    }

    public String getOriginalUrl(String tinyUrl) {
        var url = serviceRepository.findFirstByTinyUrl(tinyUrl);
        if (url.getExpiredAt().before(new Date())) {
            serviceRepository.delete(url);
            throw new RuntimeException("Url expired");
        }
        return url.getOriginalUrl();
    }


}
