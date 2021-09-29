package com.telefonica.msqueryinternalqualification.model.service;

import com.telefonica.msqueryinternalqualification.exception.NotContentException;
import com.telefonica.msqueryinternalqualification.model.dto.PagingPropertiesDTO;
import com.telefonica.msqueryinternalqualification.model.dto.QualificationResponseDTO;
import com.telefonica.msqueryinternalqualification.model.dto.ResponseDTO;
import com.telefonica.msqueryinternalqualification.model.entity.Qualification;
import com.telefonica.msqueryinternalqualification.model.enums.EConstantsVariables;
import com.telefonica.msqueryinternalqualification.model.enums.EResponseType;
import com.telefonica.msqueryinternalqualification.model.repository.IQualificationsRepository;
import com.telefonica.msqueryinternalqualification.model.task.TaskExe;
import com.telefonica.msqueryinternalqualification.util.QualificationsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * @author dpanquev
 * @version 2021-06-10
 */
@Slf4j
@Service
public class QualificationService implements IQualificationService {

    @Autowired
    private IQualificationsRepository qualificationsRepository;

    @Autowired
    private QualificationsUtils qualificationsUtils;

    @Autowired
    private TaskExe taskExe;

    /**
     * Method for search all information about qualifications channel
     *
     * @param channel
     * @param endDate
     * @param pageable
     * @param startDate
     * @return
     */
    @Transactional
    @Override
    public ResponseDTO<QualificationResponseDTO> findByChannelAndStartDateAndEndDate(String startDate, String endDate,
                                                                                     String channel, Pageable pageable) {
        Page<Qualification> qualificationPage = qualificationsRepository
                .findByChannelAndStartDateAndEndDatePageable(qualificationsUtils.strToDate(startDate)
                        , qualificationsUtils.strToDate(endDate), qualificationsUtils.separateStringForChannel(channel)
                        , pageable);
        if (qualificationPage.getContent().isEmpty()) {
            throw new NotContentException(EConstantsVariables.ERROR_FILTER.label);
        }
        return new ResponseDTO<>(
                new QualificationResponseDTO(qualificationPage.getContent(),
                        new PagingPropertiesDTO(qualificationPage.getTotalElements()
                                , (qualificationPage.getTotalPages() > 0) ? qualificationPage.getTotalPages() - 1
                                : qualificationPage.getTotalPages(),
                                qualificationPage.getNumber())),
                EResponseType.SUCCESS, EConstantsVariables.SUCCESS.label, "200");
    }

    /**
     * Method for programmer task 5s after response, and execute stored procedure
     *
     * @param channel
     * @param endDate
     * @param startDate
     * @return
     */
    @Override
    public ResponseDTO<String> exportFileFilter(String startDate, String endDate, String channel) {
        taskExe.executeTask(startDate, endDate, channel);
        return new ResponseDTO<>("Se ha programado la ejecución del stored satisfactoriamente, " +
                "consulte por favor la carpeta asociada."
                , EResponseType.SUCCESS, EConstantsVariables.PROGRAMMER.label, "200");
    }
}
