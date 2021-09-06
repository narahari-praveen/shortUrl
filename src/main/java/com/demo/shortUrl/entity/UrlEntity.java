package com.demo.shortUrl.entity;


import com.demo.shortUrl.model.UrlModel;
import com.demo.shortUrl.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.demo.shortUrl.util.Constants.LOCAL_SERVER;

@Entity
@Table(name = "Url_storage")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class UrlEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String urlUniqueId;

    private String url;

    private Integer accessedCount;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private LocalDateTime updatedDate;

    public static UrlEntity of(UrlModel urlModel) {

        return UrlEntity.builder().url(urlModel.getUrl())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .accessedCount(Constants.INTEGER_MIN).build();
    }

    public static UrlModel map(UrlEntity urlEntity) {
        return UrlModel.builder().shortenUrl(LOCAL_SERVER+urlEntity.getUrlUniqueId())
                .accessedCount(urlEntity.accessedCount).build();
    }

}
