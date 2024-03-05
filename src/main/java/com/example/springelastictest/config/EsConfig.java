package com.example.springelastictest.config;

import org.apache.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@Configuration
public class EsConfig {

    @Bean
    public RestHighLevelClient esClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("90.156.227.237", 9200, "http")));
    }
}
