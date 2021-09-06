package com.demo.shortUrl.repository;

import com.demo.shortUrl.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, String>, Serializable {


    Optional<UrlEntity> findByUrlUniqueId(String urlUniqueId);

    Optional<UrlEntity> findByUrl(String url);

}
