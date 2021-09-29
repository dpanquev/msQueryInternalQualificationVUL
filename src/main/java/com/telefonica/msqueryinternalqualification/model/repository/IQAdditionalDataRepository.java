package com.telefonica.msqueryinternalqualification.model.repository;

import com.telefonica.msqueryinternalqualification.model.entity.AdditionalData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
public interface IQAdditionalDataRepository extends JpaRepository<AdditionalData, Integer> {
}
