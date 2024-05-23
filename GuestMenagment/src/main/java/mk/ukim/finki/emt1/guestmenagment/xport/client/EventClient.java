package mk.ukim.finki.emt1.guestmenagment.xport.client;

import mk.ukim.finki.emt1.guestmenagment.domain.ValueObject.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class EventClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public EventClient(@Value("${app.EventCatalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Event> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/event").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Event>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
