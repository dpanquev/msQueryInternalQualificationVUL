package com.telefonica.msqueryinternalqualification.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.telefonica.msqueryinternalqualification.model.enums.EResponseType;
import lombok.*;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
	
	private T serviceResponse;
	private EResponseType type;
	private String message;
	private String code;

}
