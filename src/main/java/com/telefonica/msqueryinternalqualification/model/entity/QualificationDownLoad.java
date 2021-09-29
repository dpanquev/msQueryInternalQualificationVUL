package com.telefonica.msqueryinternalqualification.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dpanquev
 * @version 0.0.1
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(QualificationPK.class)
public class QualificationDownLoad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id_qualification", insertable = false, updatable = false)
    private Integer idQualification;
    @Basic(optional = false)

    @Column(name = "channel")
    private String channel;
    @Basic(optional = false)

    @Temporal(TemporalType.DATE)
    @Column(name = "creationdate")
    private Date creationDate;

    @Basic(optional = false)
    @Column(name = "idsesion")
    private String idSesion;

    @Basic(optional = false)
    @Column(name = "idtransaction")
    private String idTransaction;

    @Basic(optional = false)
    @Column(name = "producttype")
    private String productType;

    @Basic(optional = false)
    @Column(name = "productid")
    private String productId;

    @Column(name = "device")
    private String device;

    @Column(name = "idversion")
    private String idVersion;

    @Column(name = "topicid")
    private String topicId;

    @Column(name = "subtopic")
    private String subtopic;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "solution")
    private Boolean solution;

    @Column(name = "description")
    private String description;
    @Basic(optional = false)

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date timeCreate;

    @Id
    @Column(name = "id_additional_data",insertable = false, updatable = false)
    private Integer idAdditionalData;
    @Basic(optional = false)

    @Column(name = "id_qualification")
    private int idQualification_add;
    @Basic(optional = false)

    @Column(name = "data_key")
    private String dataLabel;

    @Column(name = "data_value")
    private String dataValue;
}