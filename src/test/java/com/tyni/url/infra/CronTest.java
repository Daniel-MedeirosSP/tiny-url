package com.tyni.url.infra;

import com.tyni.url.app.repositories.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class CronTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private Cron cron;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRemoveExpiredUrls() {
        cron.removeExpiredUrls();
        verify(serviceRepository).deleteByExpiredAtBefore(any(Date.class));
    }
}