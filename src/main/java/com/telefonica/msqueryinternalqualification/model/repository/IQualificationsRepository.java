package com.telefonica.msqueryinternalqualification.model.repository;

import com.telefonica.msqueryinternalqualification.model.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author dpanquev
 * @version 0.0.1
 */
@Repository
public interface IQualificationsRepository extends JpaRepository<Qualification, Integer> {
    @Query(value = "SELECT q FROM Qualification q WHERE q.creationDate >= ?1 AND q.creationDate <= ?2 AND q.channel = ?3 " +
            "ORDER BY q.idQualification ASC")
    public Page<Qualification> findByChannelAndStartDateAndEndDatePageable(Date startDate, Date endDate,
                                                                           String channel, Pageable pageable);

    @Query(value = "SELECT q FROM Qualification q WHERE q.creationDate >= ?1 AND q.creationDate <= ?2 AND q.channel = ?3 " +
            "ORDER BY q.idQualification ASC")
    public List<Qualification> findByChannelAndStartDateAndEndDate(Date startDate, Date endDate,
                                                                   String channel);

    @Query(value = "call export_csv(:p_ini_date,:p_fin_date,:p_channel,'exec')", nativeQuery = true)
    public String procedureDownload(@Param("p_ini_date") Date startDate, @Param("p_fin_date") Date endDate,
                                    @Param("p_channel") String channel);
}
