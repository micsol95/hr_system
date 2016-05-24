package ua.netcracker.model.service;


import org.springframework.http.HttpHeaders;
import ua.netcracker.model.entity.ReportQuery;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Владимир on 06.05.2016.
 */
public interface ReportService {
    Collection<Collection<String>> getReportByQuery(int courseId, String status);

    Collection<Collection<String>> getReportByQuery(String sql, String description);

    byte[] getXLSX() throws IOException;

    HttpHeaders getHeaders();

    Collection<ReportQuery> getAllShowReports();

    Collection<ReportQuery> getAllReports();

    boolean manageReportQuery(ReportQuery reportQuery, String status);

    Collection<Integer> getCourses();

    Map<Integer, String> getStatuses();
}
