package com.telefonica.msqueryinternalqualification.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.telefonica.msqueryinternalqualification.exception.BadRequestException;
import com.telefonica.msqueryinternalqualification.exception.NotContentException;
import com.telefonica.msqueryinternalqualification.model.entity.AdditionalData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telefonica.msqueryinternalqualification.model.entity.Qualification;
import com.telefonica.msqueryinternalqualification.util.QualificationsUtils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UtilTest {

    @InjectMocks
    private QualificationsUtils qualificationsUtils;

    private List<Qualification> lstQual;
    private List<Qualification> lstQualE;
    private List<Qualification> lstQualF;
    private List<AdditionalData> lstAdd;

    private ByteArrayInputStream byteArray;

    @BeforeEach
    public void before() {
        lstQual = new ArrayList<Qualification>();
        lstQualE = new ArrayList<Qualification>();
        lstQualF = new ArrayList<Qualification>();
        lstAdd = new ArrayList<>();
        lstAdd.add(new AdditionalData(1, 1, "", "", null));
        lstQual.add(new Qualification(1, "12", new Date(), "123", "123", "123", "123", "123", "123", "123", "123",
                "123", true, "123", new Date(), lstAdd));
        lstQualE.add(new Qualification(1, "12", new Date(), "123", "123", "123", "123", "123", "123", "123", "123",
                "123", true, "123", new Date(), null));
        byteArray = new ByteArrayInputStream("fileNameEmpty".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void validStrDate() {
        Date date = qualificationsUtils.strToDate("2021-08-12");
        assertNotNull(date);
    }

    @Test
    void validStrDateYear() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            qualificationsUtils.compareDate("2021-08-12", "2021-12-15");
        });
    }

    @Test
    void validStrChannel() {
        String channel = qualificationsUtils.separateStringForChannel("2021_08_12");
        assertNotNull(channel);
    }

}

