package ro.giohnny.springaifunctions.services;


import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import ro.giohnny.springaifunctions.functions.WeatherServiceFunction;
import ro.giohnny.springaifunctions.model.Answer;
import ro.giohnny.springaifunctions.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
import ro.giohnny.springaifunctions.model.WeatherRequest;
import ro.giohnny.springaifunctions.model.WeatherResponse;

import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    final ChatModel chatModel;
    @Value("${sfg.aiapp.weatherApiKey}")
    private String weatherApiKey;

    private final OpenAiChatModel openAiChatModel;

    @Override
    public Answer getAnswer(Question question) {
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(FunctionCallback.builder()
                        .function("CurrentWeather", new WeatherServiceFunction(weatherApiKey))
                        .description("Get the current weather in location")
                        .inputType(WeatherRequest.class)
                        .build()))
                .build();

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        var response = openAiChatModel.call(new Prompt(List.of(userMessage), promptOptions));

        return new Answer(response.getResult().getOutput().getContent());
    }
}
