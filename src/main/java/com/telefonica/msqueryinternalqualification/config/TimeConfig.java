package com.telefonica.msqueryinternalqualification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

/**
 * @author dpanquev
 * @version 2021-07-09
 */
@Configuration
public class TimeConfig {


    /**
     * Method for use timer and programmer task
     * return
     * */
    @Bean
    public Timer timer() {
        return new Timer();
    }
}
