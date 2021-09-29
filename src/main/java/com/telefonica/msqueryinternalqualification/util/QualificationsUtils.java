package com.telefonica.msqueryinternalqualification.util;

import com.telefonica.msqueryinternalqualification.exception.BadRequestException;
import com.telefonica.msqueryinternalqualification.model.enums.EConstantsVariables;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author dpanquev
 * @version 2021-06-10
 */
@Component
@Slf4j
public class QualificationsUtils {

    /**
     * Method convert string to date
     *
     * @param strDate
     * @return
     */
    @SneakyThrows
    public Date strToDate(String strDate) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).parse(strDate);
    }

    /**
     * Method for compare and get difference between dates
     *
     * @param strIniDate
     * @param strFinDate
     * @return
     */
    @SneakyThrows
    public void compareDate(String strIniDate, String strFinDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = sdf.parse(strIniDate);
        Date secondDate = sdf.parse(strFinDate);

        long diff = secondDate.getTime() - firstDate.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        if (difference > 31) {
            throw new BadRequestException(EConstantsVariables.ERROR_REQUEST.label);
        }

    }

    /**
     * Method convert string to date
     *
     * @param strChannelPat
     * @return
     */
    public String separateStringForChannel(String strChannelPat) {
        log.info("Canal proveniente del consumo: " + strChannelPat);
        log.info("Canal convertido: " + strChannelPat.replace("_", " "));
        return strChannelPat.replace("_", " ");
    }
}
