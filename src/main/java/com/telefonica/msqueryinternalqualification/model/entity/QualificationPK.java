
package com.telefonica.msqueryinternalqualification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QualificationPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idQualification;
    private Integer idAdditionalData;


}