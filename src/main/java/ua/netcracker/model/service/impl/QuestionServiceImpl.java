package ua.netcracker.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.netcracker.model.dao.QuestionDAO;
import ua.netcracker.model.entity.Question;
import ua.netcracker.model.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bohda on 05.05.2016.
 */
@Service()
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDAO questionDao;

    @Override
    public Collection<Question> getAllMandatory(int courseId) {
        return questionDao.findAllMandatory(courseId);
    }

    @Override
    public Collection<Question> getAll() {
        return questionDao.findAll();
    }

    @Override
    public Question get(int id) throws NullPointerException {
        return questionDao.find(id);
    }

    @Override
    public Collection<Question> getAllIsView(int courseId) {
        return questionDao.findAllMandatoryAndView(courseId);
    }

    @Override
    public Collection<Question> getAllByCourseId(int courseId) {
        return questionDao.findAllByCourseId(courseId);
    }


    @Override
    public boolean save(Question question) {
        return questionDao.insert(question);
    }

    @Override
    public boolean update(Question question) {
        return questionDao.update(question);
    }

    @Override
    public boolean remove(Question question) {
        return questionDao.delete(question);
    }

    @Override
    public void setQuestion(Question question) {
        questionDao.insert(question);
    }

    @Override
    public int getCourseId() throws NullPointerException {
        return questionDao.findCourseId();
    }

    @Override
    public int getQuantityQuestions() throws NullPointerException {
        return questionDao.findQuantityQuestions();
    }

    @Override
    public Collection<Question> getTypeOfQuestion() {
        return questionDao.findType();
    }

    @Override
    public List<String> parseListJson(List<String> list) {
        List<String> parseList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            parseList.add(list.get(i).replaceAll("[\"\\]\\[]", ""));
        }

        return parseList;
    }
}
