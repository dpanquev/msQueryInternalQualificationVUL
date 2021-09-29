package com.telefonica.msqueryinternalqualification.model.dto;

import com.telefonica.msqueryinternalqualification.model.entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QualificationResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Qualification> recordsQualificationItems;

    private PagingPropertiesDTO pagingProperties;

}
