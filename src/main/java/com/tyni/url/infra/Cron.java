package com.tyni.url.infra;

import com.tyni.url.app.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Cron {

    @Autowired
    private ServiceRepository serviceRepository;

    @Scheduled(fixedRate = 3600000) // Executa a cada 1 hora
    public void removeExpiredUrls() {
        serviceRepository.deleteByExpiredTimeBefore(new Date());
    }
}
