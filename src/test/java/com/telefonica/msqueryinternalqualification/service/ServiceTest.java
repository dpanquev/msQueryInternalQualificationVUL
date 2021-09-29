package com.telefonica.msqueryinternalqualification.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.telefonica.msqueryinternalqualification.model.entity.AdditionalData;
import com.telefonica.msqueryinternalqualification.model.enums.EConstantsVariables;
import com.telefonica.msqueryinternalqualification.model.enums.EResponseType;
import com.telefonica.msqueryinternalqualification.model.task.TaskExe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.telefonica.msqueryinternalqualification.exception.NotContentException;
import com.telefonica.msqueryinternalqualification.model.dto.QualificationResponseDTO;
import com.telefonica.msqueryinternalqualification.model.dto.ResponseDTO;
import com.telefonica.msqueryinternalqualification.model.entity.Qualification;
import com.telefonica.msqueryinternalqualification.model.repository.IQualificationsRepository;
import com.telefonica.msqueryinternalqualification.model.service.IQualificationService;
import com.telefonica.msqueryinternalqualification.model.service.QualificationService;
import com.telefonica.msqueryinternalqualification.util.QualificationsUtils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ServiceTest {

    @InjectMocks
    private QualificationService qualificationService;

    @Mock
    private IQualificationService iqualificationService;

    @Mock
    private IQualificationsRepository iqualificationRepository;

    @Mock
    private QualificationsUtils qualificationsUtils;

    @Mock
    private TaskExe taskExe;

    private Pageable pageable;

    private List<Qualification> lstQual;
    private List<AdditionalData> lstAdd;

    private List<Qualification> lstQualEm;

    private Page<Qualification> travellerPage;

    private Page<Qualification> travellerPageEmpty;

    private ByteArrayInputStream byteArray;

    private ResponseDTO responseDTO;

    @BeforeEach
    public void before() {

        int pageNumber = 0;
        int pageSize = 1;
        pageable = PageRequest.of(pageNumber, pageSize);
        lstQual = new ArrayList<Qualification>();
        lstAdd = new ArrayList<>();
        lstAdd.add(new AdditionalData(1, 1, "", "", null));
        lstQual.add(new Qualification(1, "12", new Date(), "123", "123", "123", "123", "123", "123", "123", "123",
                "123", true, "123", new Date(), lstAdd));
        travellerPage = new PageImpl<>(lstQual);
        lstQualEm = Collections.emptyList();
        travellerPageEmpty = new PageImpl<>(lstQualEm);
        byteArray = new ByteArrayInputStream("fileNameEmpty".getBytes(StandardCharsets.UTF_8));

        responseDTO = new ResponseDTO<>("Se ha programado la ejecución del stored satisfactoriamente", EResponseType.SUCCESS, EConstantsVariables.SUCCESS.label, "200");

    }

    @Test
    void validServiceQual() {
        when(iqualificationRepository.findByChannelAndStartDateAndEndDatePageable(qualificationsUtils.strToDate("2021-06-01"), qualificationsUtils.strToDate("2021-06-15"), "App_Mi_Movistar",
                pageable)).thenReturn(travellerPage);
        Page<Qualification> travellers = iqualificationRepository
                .findByChannelAndStartDateAndEndDatePageable(qualificationsUtils.strToDate("2021-06-01"), qualificationsUtils.strToDate("2021-06-15"), "App_Mi_Movistar", PageRequest.of(0, 1));
        assertEquals(1, travellers.getNumberOfElements());
    }

    @Test
    void validServiceFindChannelAndStartDateAndEndDate() {
        when(iqualificationRepository.findByChannelAndStartDateAndEndDatePageable(qualificationsUtils.strToDate("2021-06-01"), qualificationsUtils.strToDate("2021-06-15"), qualificationsUtils.separateStringForChannel("App_Mi_Movistar"),
                pageable)).thenReturn(travellerPage);
        ResponseDTO<QualificationResponseDTO> response = qualificationService
                .findByChannelAndStartDateAndEndDate("2021-06-01", "2021-06-15", qualificationsUtils.separateStringForChannel("App_Mi_Movistar"), PageRequest.of(0, 1));
        assertNotNull(response);
    }

    @Test
    void validServiceErrorFindChannelAndStartDateAndEndDate() {
        Assertions.assertThrows(NotContentException.class, () -> {
            when(iqualificationRepository.findByChannelAndStartDateAndEndDatePageable(qualificationsUtils.strToDate("2021-06-01"), qualificationsUtils.strToDate("2021-06-15"), qualificationsUtils.separateStringForChannel("App_Mi_Movistar"),
                    pageable)).thenReturn(travellerPageEmpty);
            qualificationService.findByChannelAndStartDateAndEndDate("2021-06-01", "2021-06-15", qualificationsUtils.separateStringForChannel("App_Mi_Movistar"), pageable);
        });
    }

    @Test
    void validServiceDownloadFile() throws IOException {
        ResponseDTO response = qualificationService.exportFileFilter("2021-06-01", "2021-06-15", qualificationsUtils.separateStringForChannel("App_Mi_Movistar"));
        assertNotNull(response);
    }
}
