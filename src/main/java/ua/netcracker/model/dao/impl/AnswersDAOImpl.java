package ua.netcracker.model.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.netcracker.model.dao.AnswersDAO;
import ua.netcracker.model.entity.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 06.05.2016.
 */
@Repository("answersDAO")
public class AnswersDAOImpl implements AnswersDAO {
    private static final Logger LOGGER = Logger.getLogger(AnswersDAOImpl.class);
    private static final String FIND_ALL_BY_ID = "select * from \"hr_system\".candidate_answer where candidate_id =";
    private static final String INSERT =
            "insert into \"hr_system\".candidate_answer(candidate_id, question_id, value) values(?,?,?)";
    private static final String DELETE = "DELETE FROM \"hr_system\".candidate_answer WHERE candidate_id = ";

    @Autowired
    private DataSource dataSource;

    @Override
    public Collection<Answer> findAll(int candidateId) {
        Collection<Answer> answers = new ArrayList<>();
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(FIND_ALL_BY_ID + candidateId);
            for (Map<String, Object> row : rows) {
                Answer answer = new Answer();
                answer.setQuestionId((int) row.get("question_id"));
                answer.setValue((String) row.get("value"));
                answers.add(answer);
            }
        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace());
            LOGGER.info(e.getMessage());
        }
        return answers;

    }


    @Override
    public void saveAll(Candidate candidate) {
        try {
            for (Answer answer : candidate.getAnswers()) {
                executeSaveAnswer(candidate.getId(), answer);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void deleteAnswers(int candidateId) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(DELETE + candidateId);
        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace());
            LOGGER.info(e.getMessage());
        }

    }

    private void executeSaveAnswer(int candidateId, Answer answer) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(INSERT, candidateId, answer.getQuestionId(), answer.getValue());
        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace());
            LOGGER.info(e.getMessage());
        }
    }


    @Override
    public Collection findAll() {
        return null;
    }

    @Override
    public Object find(int id) {
        return null;
    }

    @Override
    public boolean insert(Object entity) {
        return false;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }
}
