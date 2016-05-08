package ua.netcracker.model.dao;


import ua.netcracker.model.entity.ReportQuery;

import java.util.Collection;

/**
 * Created by Владимир on 28.04.2016.
 */
public interface ReportQueryDAO extends DAO<ReportQuery> {
    boolean remove(ReportQuery reportQuery);

    Collection<String> getDescriptions();

    ReportQuery getReportQueryByDescription(String description);

    Collection<ReportQuery> findAllByImportant(boolean isImportant);

    boolean updateImportance(ReportQuery reportQuery, boolean isImportant);
}
