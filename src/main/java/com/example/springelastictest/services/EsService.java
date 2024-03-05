package com.example.springelastictest.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EsService {

    public static class QuestionEntity {

        private String question;
        private String answer;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    private final static String INDEX_NAME = "questions0";

    private final ObjectMapper mapper = new ObjectMapper();

    private final RestHighLevelClient esClient;

    @Autowired
    public EsService(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }

    public void updateQuestion(String id, String question, String answer) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(question);
        questionEntity.setAnswer(answer);

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id(id);
        try {
            indexRequest.source(mapper.writeValueAsString(questionEntity), XContentType.JSON);
        } catch (JsonProcessingException e) {
            System.out.println("Error 1");
            throw new RuntimeException(e);
        }

        try {
            esClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("Error 2");
            throw new RuntimeException(e);
        }
    }



}
