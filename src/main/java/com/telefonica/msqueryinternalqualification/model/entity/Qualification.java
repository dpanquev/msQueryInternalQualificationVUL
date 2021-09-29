package com.telefonica.msqueryinternalqualification.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "qualifications")
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_qualification")
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


    @JsonIgnoreProperties(value = {"qualifications", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "qualifications", cascade = CascadeType.ALL)
    private List<AdditionalData> additionalData;

}
