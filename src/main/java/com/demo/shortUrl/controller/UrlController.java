package com.demo.shortUrl.controller;

import com.demo.shortUrl.advice.TimeTracker;
import com.demo.shortUrl.model.ResponseMetaData;
import com.demo.shortUrl.model.UrlModel;
import com.demo.shortUrl.service.IUrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/")
public class UrlController {

    private final IUrlService iUrlService;

    @TimeTracker
    @PostMapping("/shortenURL")
    protected ResponseEntity shortenURL(@Validated @RequestBody UrlModel urlModel){
        UrlModel model = iUrlService.shortenURL(urlModel);
        model.setResponseMetaData(ResponseMetaData.builder().code(200l).build());
        return ResponseEntity.ok(model);
    }

    @TimeTracker
    @GetMapping("/{shortUrlId}")
    protected void redirectToURL(@PathVariable String shortUrlId, HttpServletResponse httpServletResponse){
        String url = iUrlService.redirectToURL(shortUrlId);
        try {
            httpServletResponse.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*return ResponseEntity.status(HttpStatus.SEE_OTHER.value()).location(URI.create(url)).build();*/

    }

    @TimeTracker
    @GetMapping("/getAllShortUrls")
    protected ResponseEntity getAllShortUrls(){
        List<UrlModel> listOfShortUrls =  iUrlService.getAllShortUrls();
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(UrlModel.builder().listOfShortUrls(listOfShortUrls).responseMetaData(ResponseMetaData.builder().code(200l).build()).build());
    }

}
