package ro.giohnny.springaifunctions.services;


import ro.giohnny.springaifunctions.model.Answer;
import ro.giohnny.springaifunctions.model.Question;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OpenAIService {

    Answer getAnswer(Question question);
    Answer getStockPrice(Question question);
}
