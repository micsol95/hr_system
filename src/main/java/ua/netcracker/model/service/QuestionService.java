package ua.netcracker.model.service;

import org.springframework.stereotype.Service;
import ua.netcracker.model.entity.Question;

import java.util.Collection;
import java.util.List;

/**
 * Created by bohda on 05.05.2016.
 */
@Service
public interface QuestionService {
    Collection<Question> getAllMandatory(int courseId);

    Collection<Question> getAll();

    Question get(int id);

    Collection<Question> getAllIsView(int courseId);

    Collection<Question> getAllByCourseId(int courseId);

    boolean save(Question question);

    boolean update(Question question);

    boolean remove(Question question);

    public Collection<String> parseListJson(List<String> list);

    public Collection<Question> getTypeOfQuestion();

    public int getQuantityQuestions();

    public void setQuestion(Question question);

    public int getCourseId();



}
