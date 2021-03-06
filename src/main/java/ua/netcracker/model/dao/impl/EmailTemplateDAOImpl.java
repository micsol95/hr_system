package ua.netcracker.model.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.netcracker.model.dao.EmailTemplateDAO;
import ua.netcracker.model.entity.EmailTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Владимир on 28.04.2016.
 */
@Repository("emailTemplateDAO")
public class EmailTemplateDAOImpl implements EmailTemplateDAO {
    static final Logger LOGGER = Logger.getLogger(EmailTemplateDAOImpl.class);

    @Autowired
    private DataSource dataSource;

    private static final String SQL_FIND_ALL = "SELECT * FROM \"hr_system\".email_template;";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM \"hr_system\".email_template WHERE id = (?);";
    private static final String SQL_INSERT = "INSERT INTO \"hr_system\".email_template (description, template) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE \"hr_system\".email_template SET description=?, template=? WHERE id = (?);";

    @Override
    public Collection<EmailTemplate> findAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_FIND_ALL);
            Collection<EmailTemplate> emailTemplates = new ArrayList<>();
            for (Map row : rows) {
                EmailTemplate emailTemplate = new EmailTemplate();
                emailTemplate.setId((Integer) row.get("id"));
                emailTemplate.setDescription((String) row.get("description"));
                emailTemplate.setTemplate((String) row.get("template"));
                emailTemplates.add(emailTemplate);
            }
            return emailTemplates;
        } catch (DataAccessException ex) {
            LOGGER.trace(ex);
            return new ArrayList<>();
        }
    }

    @Override
    public EmailTemplate find(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            EmailTemplate emailTemplate = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, new RowMapper<EmailTemplate>() {
                        @Override
                        public EmailTemplate mapRow(ResultSet resultSet, int i) throws SQLException {
                            return getEmailTemplate(resultSet);
                        }
                    }
            );
            return emailTemplate;
        } catch (DataAccessException ex) {
            LOGGER.trace(ex);
            return new EmailTemplate();
        }
    }

    private EmailTemplate getEmailTemplate(ResultSet resultSet) throws SQLException {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setId(resultSet.getInt("id"));
        emailTemplate.setDescription(resultSet.getString("description"));
        emailTemplate.setTemplate(resultSet.getString("template"));
        return emailTemplate;
    }

    @Override
    public boolean insert(EmailTemplate emailTemplate) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(SQL_INSERT, new Object[]{emailTemplate.getDescription(),
                    emailTemplate.getTemplate()
            });
            return true;
        } catch (DataAccessException ex) {
            LOGGER.trace(ex);
            return false;
        }
    }

    @Override
    public boolean update(EmailTemplate emailTemplate) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(SQL_UPDATE, new Object[]{emailTemplate.getDescription(),
                    emailTemplate.getTemplate(), emailTemplate.getId()
            });
            return true;
        } catch (DataAccessException ex) {
            LOGGER.trace(ex);
            return false;
        }
    }
}
