package ru.practicum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.List;
import java.util.Map;

@Component
public class StatsClient extends BaseClient {

    @Autowired
    public StatsClient(@Value("${stats-service.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new).build());
    }

    public ResponseEntity<Object> saveHit(EndpointHitDto endpointHit) {
        return post("/hit", endpointHit);
    }

    public ResponseEntity<Object> getViewStats(String start, String end, List<String> uris, boolean unique) {
        Map<String, Object> params = Map.of("start", start, "end", end, "uris", uris, "unique", unique);
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", 1L, params);
    }
}
