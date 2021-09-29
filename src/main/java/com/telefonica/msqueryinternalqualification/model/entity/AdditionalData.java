package com.telefonica.msqueryinternalqualification.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additional_data")
public class AdditionalData implements Serializable {

	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_additional_data")
    private Integer idAdditionalData;
    @Basic(optional = false)

    @Column(name = "id_qualification")
    private int idQualification;
    @Basic(optional = false)

    @Column(name = "data_key")
    private String dataLabel;

    @Column(name = "data_value")
    private String dataValue;

    @JoinColumn(name="id_qualification", nullable=false, updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Qualification qualifications;
}
