package com.tyni.url.app.repositories;

import com.tyni.url.domain.UrlDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ServiceRepository extends MongoRepository<UrlDomain, String> {

    UrlDomain findFirstByTinyUrl(String tinyUrl);
    
    void deleteByExpiredAtBefore(Date date);
}
