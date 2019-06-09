package de.tuchemnitz.de.Main;

import de.tuchemnitz.de.Main.entity.Web_feed;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Feed_operation {
    public static final String REST_SERVICE_URI = "http://localhost:8080/";
    static RestTemplate restTemplate;

//    public static deleteSingleFeed(int providerid){
//        restTemplate = new RestTemplate();
//
//        ResponseEntity<List<Web_feed>> re1 = restTemplate.exchange(REST_SERVICE_URI+"/feeds/link",
//                HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Web_feed>>(){});
//
//    }

}
