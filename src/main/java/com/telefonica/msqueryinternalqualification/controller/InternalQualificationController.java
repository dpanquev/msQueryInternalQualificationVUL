package com.telefonica.msqueryinternalqualification.controller;

import com.telefonica.msqueryinternalqualification.model.dto.QualificationResponseDTO;
import com.telefonica.msqueryinternalqualification.model.dto.ResponseDTO;
import com.telefonica.msqueryinternalqualification.model.service.IQualificationService;
import com.telefonica.msqueryinternalqualification.util.QualificationsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


/**
 * @author dpanquev
 * @version 2021-06-10
 */
@RestController
@RequestMapping("/telefonica/customer/v1/queryQualification")
@Validated
@Slf4j
public class InternalQualificationController {

    @Autowired
    private IQualificationService qualificationService;

    @Autowired
    private QualificationsUtils qualificationsUtils;

    /**
     * Method for receive all information and search by channel and date filter
     *
     * @param channel
     * @param endDate
     * @param page
     * @param startDate
     * @param recordNumberByPage
     * @return
     */
    @GetMapping(value = "/filter/{channel}/{startDate}/{endDate}/{recordNumberByPage}/{page}", produces = "application/json;charadd=UTF-8")
    public ResponseEntity<ResponseDTO<QualificationResponseDTO>> index(
            @RequestHeader(value = "Authorization", required = true) String authorizationH,
            @RequestHeader(value = "operation", required = true) String operationH,
            @Pattern(regexp = "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}", message = "Se espera el formato pra el campo UUID de forma canónica con 32 dígitos hexadecimales, ej: 550e8400-e29b-41d4-a716-446655440001")
            @RequestHeader(value = "execId", required = true) String execIdH,
            @PathVariable @NotEmpty(message = "El campo Canal es requerido") String channel,
            @PathVariable @Pattern(regexp = "^(\\d{4})([-])(\\d{2})([-])(\\d{2})+$", message = "El formato de Fecha Inicio no es válido, debe ser yyyy-mm-dd") @NotEmpty(message = "El campo Fecha inicio es requerido") String startDate,
            @PathVariable @Pattern(regexp = "^(\\d{4})([-])(\\d{2})([-])(\\d{2})+$", message = "El formato de Fecha Fin no es válido, debe ser yyyy-mm-dd") @NotEmpty(message = "El campo Fecha Fin es requerido") String endDate,
            @PathVariable Integer recordNumberByPage, @PathVariable Integer page) {
        log.info("Canal que llega desde el controlador para filtros: " + channel);
        qualificationsUtils.compareDate(startDate, endDate);

        return ResponseEntity.ok().body(qualificationService.findByChannelAndStartDateAndEndDate(startDate, endDate, channel,
                PageRequest.of(page, recordNumberByPage)));
    }

    /**
     * Method for receive all information and search by channel, date filter and download file csv
     *
     * @param channel
     * @param endDate
     * @param startDate
     * @return
     */
    @GetMapping(value = "/download/{channel}/{startDate}/{endDate}/{format}", produces = "application/json;charadd=UTF-8")
    public ResponseEntity<ResponseDTO<String>> downloadFile(
            @RequestHeader(value = "Authorization", required = true) String authorizationH,
            @RequestHeader(value = "operation", required = true) String operationH,
            @Pattern(regexp = "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}", message = "Se espera el formato pra el campo UUID de forma canónica con 32 dígitos hexadecimales, ej: 550e8400-e29b-41d4-a716-446655440001")
            @RequestHeader(value = "execId", required = true) String execIdH,
            @PathVariable @NotEmpty(message = "El campo Canal es requerido") String channel,
            @PathVariable @Pattern(regexp = "^(\\d{4})([-])(\\d{2})([-])(\\d{2})+$", message = "El formato de Fecha Inicio no es válido, debe ser yyyy-mm-dd") @NotEmpty(message = "El campo Fecha inicio es requerido") String startDate,
            @PathVariable @Pattern(regexp = "^(\\d{4})([-])(\\d{2})([-])(\\d{2})+$", message = "El formato de Fecha Fin no es válido, debe ser yyyy-mm-dd") @NotEmpty(message = "El campo Fecha Fin es requerido") String endDate,
            @PathVariable @NotEmpty(message = "El campo Formato es requerido") String format) {
        qualificationsUtils.compareDate(startDate, endDate);
        log.info("Canal que llega desde el controlador para descargas: " + channel);
        return ResponseEntity.ok()
                .body(qualificationService.exportFileFilter(startDate, endDate,
                        channel));
    }
}
