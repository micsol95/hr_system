package ua.netcracker.model.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.netcracker.model.dao.InterviewDaysDetailsDAO;
import ua.netcracker.model.dao.impl.InterviewDaysDetailsDAOImpl;
import ua.netcracker.model.entity.InterviewDaysDetails;
import ua.netcracker.model.service.InterviewDaysDetailsService;
import ua.netcracker.model.service.ReportService;
import ua.netcracker.model.utils.JdbcTemplateFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by MaXim on 05.05.2016.
 */
@Service("Interview Service")
public class InterviewDaysDetailsServiceImpl implements InterviewDaysDetailsService {
    static final Logger LOGGER = Logger.getLogger(InterviewDaysDetailsServiceImpl.class);

    private static final String INTERVIEW_DETAILS_ADDRESS_SQL = "SELECT hr_system.interview_days_details.id, date, start_time, end_time, hr_system.address.address, hr_system.address.room_capacity" +
            " FROM hr_system.interview_days_details" +
            " LEFT JOIN hr_system.address" +
            " ON hr_system.interview_days_details.address_id=hr_system.address.id";
    @Autowired
    InterviewDaysDetailsDAO interviewDaysDetailsDAO;

    @Autowired
    private JdbcTemplateFactory jdbcTemplateFactory;

    @Autowired
    private ReportServiceImpl reportService;

    @Autowired
    private DataSource dataSource;

    InterviewDaysDetails interviewDaysDetails;

    @Override
    public boolean add(InterviewDaysDetails interviewDaysDetails) {
        return interviewDaysDetailsDAO.insert(interviewDaysDetails);
    }

    @Override
    public InterviewDaysDetails findById(int id) {
        return interviewDaysDetailsDAO.find(id);
    }

    @Override
    public List<InterviewDaysDetails> findAll() {
        return (List<InterviewDaysDetails>) interviewDaysDetailsDAO.findAll();
    }

    @Override
    public void update(InterviewDaysDetails interviewDaysDetails) {
//        if (interviewDaysDetails.getId() > 0) {
//            interviewDaysDetailsDAO.insert(interviewDaysDetails);
//        } else {
        interviewDaysDetailsDAO.update(interviewDaysDetails);
//        }
    }

    @Override
    public void remove(int id) {
        interviewDaysDetailsDAO.remove(id);
    }

    @Override
    public String getStartTimeofInterview(int id) {
        return interviewDaysDetailsDAO.find(id).getStartTime();
    }

    @Override
    public String getEndTimeofInterview(int id) {
        return interviewDaysDetailsDAO.find(id).getEndTime();
    }

    @Override
    public String getDateofInterview(int id) {
        return interviewDaysDetailsDAO.find(id).getInterviewDate();
    }

    public int getIdbyDate(String date) {
        String sql = "SELECT id FROM hr_system.interview_days_details WHERE date = " + "\'" + date + "\'";
        int id = (Integer) jdbcTemplateFactory.getJdbcTemplate(dataSource).queryForObject(sql, Integer.class);
        return id;
    }

    public List<Map<String, Object>> findAllInterviewDetailsAddress() {
            return jdbcTemplateFactory.getJdbcTemplate(dataSource).queryForList(INTERVIEW_DETAILS_ADDRESS_SQL);
    }
}
