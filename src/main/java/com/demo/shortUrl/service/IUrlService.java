package com.demo.shortUrl.service;

import com.demo.shortUrl.model.UrlModel;

import java.util.List;

public interface IUrlService {

    UrlModel shortenURL(UrlModel urlModel);

    String redirectToURL(String shortUrlId);

    List<UrlModel> getAllShortUrls();
}
