package com.bridgelabz.fundooappbackend.elastic.configuration;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Elastic Search Configurations
 *
 ***********************************************************************************************************/
@Configuration
public class ElasticSearchConfig {

  //  @Value("${elasticsearch.host}")
 //   private String elasticsearchHost;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        return client;
    }
}
