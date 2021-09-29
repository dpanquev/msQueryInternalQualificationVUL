package com.telefonica.msqueryinternalqualification.model.task;

import com.telefonica.msqueryinternalqualification.model.repository.IQualificationsRepository;
import com.telefonica.msqueryinternalqualification.util.QualificationsUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dpanquev
 * @version 2021-09-07
 */
@Component
@Slf4j
public class TaskExe {

    @Autowired
    private IQualificationsRepository qualificationsRepository;

    @Autowired
    private QualificationsUtils qualificationsUtils;

    /**
     * Method for execute stored procedure
     *
     * @param channel
     * @param endDate
     * @param startDate
     */
    @SneakyThrows
    public void executeTask(String startDate, String endDate, String channel) {
        log.info("Programmer task for execute stored procedure");
        log.info(new Date().toString());
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable task2 = () -> {

            /**
             * Task for consume stored procedure
             * */
            log.info("Execute stored procedure");
            String exec = qualificationsRepository.procedureDownload(qualificationsUtils.strToDate(startDate)
                    , qualificationsUtils.strToDate(endDate), qualificationsUtils.separateStringForChannel(channel));
            log.info(exec);
            if (exec.equalsIgnoreCase("204")) {
                log.error("No Content consulting init_date: ".concat(startDate)
                        .concat(" end_date: ").concat(endDate).concat(" channel: ").concat(channel));
            }
            if (exec.equalsIgnoreCase("500")) {
                log.error("Don't execute stored procedure".concat(startDate)
                        .concat(" end_date: ").concat(endDate).concat(" channel: ").concat(channel));
            }
        };
        ses.schedule(task2, 5, TimeUnit.SECONDS);

        ses.shutdown();
    }
}
