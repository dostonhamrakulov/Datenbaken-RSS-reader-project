package de.tuchemnitz.de.Main;

import de.tuchemnitz.de.Main.entity.Web_feed;
import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static de.tuchemnitz.de.Main.Common_code.REST_SERVICE_URI;

public class Feed_operation {
    static RestTemplate restTemplate;

    public static void deleteSingleFeed(int providerid){
        restTemplate = new RestTemplate();

        ResponseEntity<String> re1 = restTemplate.exchange(REST_SERVICE_URI+"feeds/delete-by-providerid/?providerid="+providerid,
                HttpMethod.DELETE, null, String.class);

        if (re1.getBody() == "Delete"){
            System.out.println("Deleted");
        }

    }

    public static Web_feed_providers getProviderInfo(int id){
        restTemplate = new RestTemplate();

        ResponseEntity<Web_feed_providers> re1 = restTemplate.exchange(REST_SERVICE_URI+"web-feed-provider/"+id,
                HttpMethod.GET, null, Web_feed_providers.class);
        return re1.getBody();
    }

}
