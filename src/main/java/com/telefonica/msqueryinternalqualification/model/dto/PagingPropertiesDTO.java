package com.telefonica.msqueryinternalqualification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagingPropertiesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long registersNumberTotal;

    private int pageTotal;

    private int actualPage;
}
