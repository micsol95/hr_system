package ua.netcracker.model.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.netcracker.model.dao.CandidateDAO;
import ua.netcracker.model.entity.Candidate;
import ua.netcracker.model.entity.Status;
import ua.netcracker.model.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("candidateDao")
public class CandidateDAOImpl implements CandidateDAO {
    private static final Logger LOGGER = Logger.getLogger(CandidateDAOImpl.class);
    private static final String FIND_INTERVIEW_DAYS_DETAILS_ID =
            "SELECT interview_days_details FROM \"hr_system\".candidate WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM \"hr_system\".candidate WHERE id = ";
    private static final String FIND_ALL = "SELECT * FROM \"hr_system\".candidate";
    private static final String FIND_ALL_BY_COURSE = "SELECT * FROM \"hr_system\".candidate WHERE course_id = ";
    private static final String FIND_STATUS_BY_ID = "SELECT * FROM \"hr_system\".status WHERE id = ?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM \"hr_system\".candidate WHERE user_id = ?";
    private static final String UPDATE = "UPDATE \"hr_system\".candidate SET(status_id,interview_days_details_id)=(?,?) " +
            " WHERE id = ? ";
    private static final String FIND_BY_STATUS = "SELECT * FROM \"hr_system\".candidate WHERE status_id =?";
    private static final String FIND_ALL_STATUS = "SELECT * FROM \"hr_system\".status";
    private static final String UPDATE_STATUS = "UPDATE \"hr_system\".candidate SET status_id=(?)" +
            "WHERE id=?";
    private static final String PAGINATION = "SELECT u.name,u.email ,u.surname, u.patronymic, candidate.id, candidate.status_id, candidate.course_id " +
            "FROM \"hr_system\".users u " +
            "JOIN \"hr_system\".role_users_maps rol " +
            "ON rol.user_id = u.id JOIN \"hr_system\".candidate candidate " +
            "ON candidate.user_id = u.id WHERE rol.role_id = 5 " +
            "ORDER BY candidate.course_id DESC, candidate.status_id DESC,  candidate.id LIMIT ";
    private static final String LAST_ROWS = "SELECT id FROM \"hr_system\".candidate ORDER BY id DESC limit 1";



    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Override
    public Collection<Candidate> findCandidateByStatus(String status) {
        Collection<Candidate> listCandidate = null;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            List<Map<String, Object>> rows = jdbcTemplate.
                    queryForList(FIND_BY_STATUS, Status.valueOf(status).getId());
            for (Map<String, Object> row : rows) {
                Candidate candidate = new Candidate();
                candidate.setId((int) row.get("id"));
                candidate.setUserId((int) row.get("user_id"));
                candidate.setStatusId((int) row.get("status_id"));
                candidate.setInterviewDaysDetailsId((int) row.get("interview_days_details_id"));
                candidate.setCourseId((int) row.get("course_id"));
                listCandidate.add(candidate);
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }

        return listCandidate;
    }

    @Override
    public int findInterviewDetailsByCandidateId(Integer candidateId) {
        int interviewDaysDetails = 0;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            interviewDaysDetails = jdbcTemplate.queryForObject(FIND_INTERVIEW_DAYS_DETAILS_ID,
                    new RowMapper<Integer>() {
                        @Override
                        public Integer mapRow(ResultSet rs, int rowNum) {
                            int details = 0;
                            try {
                                details = rs.getInt("interview_days_details");
                            } catch (SQLException e) {
                                LOGGER.info("SQLState: " + e.getSQLState() + " Message: " + e.getMessage());
                                LOGGER.debug(e.getStackTrace(), e);
                            }
                            return details;
                        }
                    }, candidateId);
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }
        return interviewDaysDetails;
    }


    @Override
    public Candidate findByCandidateId(Integer candidateId) {
        Candidate candidate = null;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            candidate = jdbcTemplate.queryForObject(FIND_BY_ID + candidateId, new RowMapper<Candidate>() {
                        @Override
                        public Candidate mapRow(ResultSet rs, int rowNum) {
                            Candidate candidate = new Candidate();
                            try {
                                candidate.setId(rs.getInt("id"));
                                candidate.setUserId(rs.getInt("user_id"));
                                candidate.setStatusId(rs.getInt("status_id"));
                                candidate.setCourseId(rs.getInt("course_id"));
                            } catch (SQLException e) {
                                LOGGER.info("SQLState: " + e.getSQLState() + " Message: " + e.getMessage());
                                LOGGER.debug(e.getStackTrace(), e);
                            }
                            return candidate;
                        }
                    }
            );
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }
        return candidate;

    }


    @Override
    public Status findStatusById(Integer statusId) {
        String value = null;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            value = jdbcTemplate.queryForObject(FIND_STATUS_BY_ID, new RowMapper<String>() {
                        @Override
                        public String mapRow(ResultSet rs, int rowNum) {
                            String value = null;
                            try {
                                value = rs.getString("value");
                            } catch (SQLException e) {
                                LOGGER.info("SQLState: " + e.getSQLState() + " Message: " + e.getMessage());
                                LOGGER.debug(e.getStackTrace(), e);
                            }
                            return value;
                        }
                    }, statusId
            );
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }
        return Status.valueOf(value);
    }

    public Candidate findByUserId(Integer userId) {
        Candidate candidate = new Candidate();

        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(FIND_BY_USER_ID, userId);
            for (Map row : rows) {
                candidate.setId((int) row.get("id"));
                candidate.setUserId((int) row.get("user_Id"));
                candidate.setStatusId((int) row.get("status_id"));
                candidate.setCourseId((int) row.get("course_id"));
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }

        return candidate;
    }

    @Override
    public Collection<Candidate> findAllByCourse(Integer courseId) {
        return findCandidates(FIND_ALL_BY_COURSE + courseId);
    }


    public boolean saveCandidate(Candidate candidate) {
        try {
            simpleJdbcInsert = new SimpleJdbcInsert(dataSource).
                    withTableName("\"hr_system\".candidate").
                    usingColumns("user_id", "status_id", "course_id")
                    .usingGeneratedKeyColumns("id");
            MapSqlParameterSource insertParameter = new MapSqlParameterSource();
            insertParameter.addValue("user_id", candidate.getUserId());
            insertParameter.addValue("status_id", candidate.getStatusId());
            insertParameter.addValue("course_id", candidate.getCourseId());
            simpleJdbcInsert.execute(insertParameter);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }

        return false;
    }

    @Override
    public Map<Integer, String> findAllStatus() {
        Map<Integer, String> status = new HashMap<>();
        jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(FIND_ALL_STATUS);
            for (Map row : rows) {
                status.put((int) row.get("id"), row.get("value").toString());
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }
        return status;
    }

    @Override
    public boolean updateCandidateStatus(Integer candidateID, Integer newStatusID) {
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(UPDATE_STATUS, newStatusID, candidateID);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error:" + e);
        }
        return false;

    }

    @Override
    public Candidate find(int id) {
        return null;
    }

    @Override
    public boolean insert(Candidate entity) {
        return false;
    }


    @Override
    public boolean update(Candidate candidate) {
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(UPDATE, candidate.getStatusId(), candidate.getInterviewDaysDetailsId(), candidate.getId());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error:" + e);
        }
        return false;
    }


    @Override
    public Collection<Candidate> findAll() {
        return findCandidates(FIND_ALL);
    }

    private Collection<Candidate> findCandidates(String sql) {
        Collection<Candidate> listCandidates = new ArrayList<>();
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            for (Map<String, Object> row : rows) {
                Candidate candidate = new Candidate();
                candidate.setId((int) row.get("id"));
                candidate.setUserId((int) row.get("user_id"));
                candidate.setStatusId((int) row.get("status_id"));
                candidate.setStatus(findStatusById(candidate.getStatusId()));
                candidate.setCourseId((int) row.get("course_id"));
                listCandidates.add(candidate);
            }
        } catch (Exception e) {
            LOGGER.error("Error:" + e);
        }

        return listCandidates;

    }
    @Override
    public Collection<Candidate> pagination(Integer elementPage, Integer fromElement) {


        Collection<Candidate> listCandidates;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            listCandidates = jdbcTemplate.query(PAGINATION + elementPage + " offset " + fromElement, new RowMapper<Candidate>() {
                @Override
                public Candidate mapRow(ResultSet resultSet, int i) throws SQLException {
                    Candidate candidate = new Candidate();
                    User user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setPatronymic(resultSet.getString("patronymic"));
                    user.setEmail(resultSet.getString("email"));
                    candidate.setUser(user);
                    candidate.setId(resultSet.getInt("id"));
                    candidate.setStatusId(resultSet.getInt("status_id"));
                    candidate.setCourseId(resultSet.getInt("course_id"));
                    return candidate;
                }
            });
           return listCandidates;
        } catch (Exception e) {
            LOGGER.error("Error:" + e);
        }

        return null;

    }

    @Override
    public Integer getRows() {
        Candidate candidate = new Candidate();

        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            candidate = jdbcTemplate.queryForObject(LAST_ROWS, new RowMapper<Candidate>() {
                @Override
                public Candidate mapRow(ResultSet resultSet, int i) throws SQLException {
                    Candidate candidate = new Candidate();
                    candidate.setId(resultSet.getInt("id"));
                    return candidate;
                }
            });

        } catch (Exception e) {
            LOGGER.error("Error: " + e);
        }

        return candidate.getId();
    }

    @Override
    public Collection<Candidate> findForSerach(Integer elementPage, Integer fromElement, String find) {

        int d=2000000000;
        try {
            d = Integer.valueOf(find);
        } catch (Exception e) {

        }

        Collection<Candidate> listCandidates;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            listCandidates = jdbcTemplate.query(
                    "SELECT candidate.id, u.name, surname, patronymic, email, " +
                            "status_id, course_id, value , answer.question_id " +
                            "FROM \"hr_system\".users u " +
                            "JOIN \"hr_system\".role_users_maps rol ON rol.user_id = u.id " +
                            "JOIN \"hr_system\".candidate candidate ON candidate.user_id = u.id " +
                            "JOIN \"hr_system\".candidate_answer answer " +
                            "ON candidate.id = answer.candidate_id " +
                            "WHERE answer.question_id = 3 and " +
                            "rol.role_id = 5 " +
                            "and( name LIKE '%"+find+"%' " +
                            "or surname LIKE '%"+find+"%' " +
                            "or patronymic LIKE '%"+find+"%' " +
                            "or email LIKE '%"+find+"%' " +
                            "or value LIKE '%"+find+"%'" +
                            "or candidate.id = "+d+") " +
                            "ORDER BY candidate.course_id DESC, " +
                            "candidate.status_id DESC,  candidate.id " +
                            "limit "+elementPage+" offset "+fromElement,
                    new RowMapper<Candidate>() {
                        @Override
                        public Candidate mapRow(ResultSet resultSet, int i) throws SQLException {
                            Candidate candidate = new Candidate();
                            User user = new User();
                            user.setName(resultSet.getString("name"));
                            user.setSurname(resultSet.getString("surname"));
                            user.setPatronymic(resultSet.getString("patronymic"));
                            user.setEmail(resultSet.getString("email"));
                            candidate.setUser(user);
                            candidate.setId(resultSet.getInt("id"));
                            candidate.setStatusId(resultSet.getInt("status_id"));
                            candidate.setCourseId(resultSet.getInt("course_id"));
                            return candidate;
                        }
                    });
            return listCandidates;
        } catch (Exception e) {
            LOGGER.error("Error:" + e);
        }

        return null;
    }
    @Override
    public long rowsFind(String find) {

        int id=2000000000;
        try {
            id = Integer.valueOf(find);
        } catch (Exception e) {

        }

        long rows;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            rows = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) " +
                            "FROM \"hr_system\".users u " +
                            "JOIN \"hr_system\".role_users_maps rol ON rol.user_id = u.id " +
                            "JOIN \"hr_system\".candidate candidate ON candidate.user_id = u.id " +
                            "JOIN \"hr_system\".candidate_answer answer " +
                            "ON candidate.id = answer.candidate_id " +
                            "WHERE answer.question_id = 3 and " +
                            "rol.role_id = 5 " +
                            "and( name LIKE '%" + find + "%' " +
                            "or surname LIKE '%" + find + "%' " +
                            "or patronymic LIKE '%" + find + "%' " +
                            "or email LIKE '%" + find + "%' " +
                            "or value LIKE '%" + find + "%'" +
                            "or candidate.id = " + id + ")", new RowMapper<Long>() {
                        @Override
                        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                            long row = resultSet.getLong("count");
                            return row;
                        }
                    });
            return rows;
        } catch (Exception e) {
            LOGGER.error("Error:" + e);
        }

        return 0;
    }
}
