package com.demo.shortUrl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
@Builder
public class ResponseMetaData {

	private String message;
	
	private Long processingTime;

	private Long code;

}
