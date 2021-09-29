package com.telefonica.msqueryinternalqualification.model.service;

import com.telefonica.msqueryinternalqualification.model.dto.QualificationResponseDTO;
import com.telefonica.msqueryinternalqualification.model.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;

/**
 * @version 0.0.1
 * @author dpanquev
 * */
public interface IQualificationService {


    public ResponseDTO<QualificationResponseDTO> findByChannelAndStartDateAndEndDate(String startDate, String endDate
            , String channel, Pageable pageable);

    public ResponseDTO<String> exportFileFilter(String startDate, String endDate
            , String channel);
}
