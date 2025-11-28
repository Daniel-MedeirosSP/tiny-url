package com.tyni.url.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "url")
public class UrlDomain {

    private String id;

    private String originalUrl;

    private String tinyUrl;

    private Date createdAt;

    private Date expiredAt;
}
