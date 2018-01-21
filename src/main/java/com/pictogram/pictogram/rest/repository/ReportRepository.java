package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.report.Report;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface ReportRepository extends CrudRepository<Report, Long> {
}
