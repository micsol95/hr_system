package ua.netcracker.model.service;


import ua.netcracker.model.entity.*;

import java.util.Collection;
import java.util.Map;

/**
 * @author Alex
 */

public interface CandidateService {
    Collection<Candidate> getCandidateByStatus(String status);

    Collection<Candidate> getAllCandidates();

    Collection<Candidate> getPartCandidatesWithAnswer(Integer with, Integer to);

    Collection<Candidate> getPartCandidatesIsViewWithAnswer(Integer with, Integer to);

    Collection<Candidate> getAllByCourse(Integer courseId);

    Collection<Answer> getAnswersIsView(Candidate candidate, Collection<Question> listQuestions);

    Collection<Candidate> getPartByCourse(Integer courseId, Integer with, Integer to);

    Candidate getCandidateById(Integer id);

    Integer getCandidateCount();

    Integer getCandidateCountByInterviewId(Integer interviewId);

    Candidate getCandidateByUserId(Integer userId);

    Status getStatusById(Integer statusId);

    Map<Integer, Integer> getMarks(Integer candidateId);

    Map<Integer, String> getRecommendations(Integer id);

    Map<Integer, String> getComments(Integer id);

    int getInterviewDayDetailsById(Integer id);

    Collection<Answer> getAllCandidateAnswers(Candidate candidate);

    boolean saveCandidate(Candidate candidate);

    boolean updateCandidate(Candidate candidate);

    Candidate saveAnswers(String answersJsonString);

    Candidate getCurrentCandidate();

    void deleteAnswers(Candidate candidate);

    void saveOrUpdateAnswers(Candidate candidate);

    Collection<Answer> getAnswerByQuestionId(Candidate candidate, int questionId);

    Map<Integer, String> getAllStatus();

    boolean updateCandidateStatus(Integer candidateID, Integer newStatusID);

    public Collection<Candidate> getCandidate(Integer limitRows, Integer fromElement, String find);

    Collection<Candidate> getAllMarkedByCurrentInterviewer(User user);

    Collection<Candidate> getPartCandidates(Integer with, Integer to);

    boolean updateInterviewResult(Integer candidateId, InterviewResult interviewResult);

    boolean saveInterviewResult(Integer candidateId, Integer interviewerId, Integer mark, String recomendation, String comment);

}
