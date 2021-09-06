package com.demo.shortUrl.service;


import com.demo.shortUrl.entity.UrlEntity;
import com.demo.shortUrl.model.UrlModel;
import com.demo.shortUrl.repository.UrlRepository;
import com.demo.shortUrl.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.shortUrl.util.Constants.LOCAL_SERVER;

@Service
@AllArgsConstructor
public class UrlService implements IUrlService{

    private final UrlRepository urlRepository;

    @Override
    @Transactional
    public UrlModel shortenURL(UrlModel urlModel) {
        UrlEntity urlEntity;
        Optional<UrlEntity> UrlEntityOptional = urlRepository.findByUrl(urlModel.getUrl());
        if(UrlEntityOptional.isPresent()) {
            urlEntity = UrlEntityOptional.get();
        }else {
            urlEntity = urlRepository.save(UrlEntity.of(urlModel));
            String uniqueUrl = Utils.getUniqueID(urlEntity.getId());
            urlEntity.setUrlUniqueId(uniqueUrl);
        }
        urlModel = processShortUrl(urlEntity);
        return urlModel;
    }

    @Override
    @Transactional
    public String redirectToURL(String shortUrlId) {
        Optional<UrlEntity> UrlEntityOptional = urlRepository.findByUrlUniqueId(shortUrlId);
        if(UrlEntityOptional.isPresent()) {
            UrlEntity urlEntity = UrlEntityOptional.get();
            urlEntity.setAccessedCount(urlEntity.getAccessedCount()+1);
            urlRepository.save(urlEntity);

            return urlEntity.getUrl();
        }else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"URL NOT FOUND");
        }

    }

    @Override
    public List<UrlModel> getAllShortUrls() {
        List<UrlEntity> urlEntities = urlRepository.findAll();
        if(Objects.nonNull(urlEntities) && urlEntities.size()>0) {
            return urlEntities.stream().map(UrlEntity::map).collect(Collectors.toList());
        }else{
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"URL NOT FOUND");
        }
    }

    private UrlModel processShortUrl(UrlEntity urlEntity) {
        return UrlModel.builder().shortenUrl(LOCAL_SERVER+urlEntity.getUrlUniqueId()).build();
    }


}
