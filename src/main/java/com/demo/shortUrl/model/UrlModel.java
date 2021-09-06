package com.demo.shortUrl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UrlModel {
    private String url;

    private String shortenUrl;

    private Integer accessedCount;

    private List<UrlModel> listOfShortUrls;

    private ResponseMetaData responseMetaData;
}
