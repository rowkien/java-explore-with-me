package ru.practicum.viewstats;


import ru.practicum.EndpointHitDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ViewStatsService {
    EndpointHitDto saveHit(EndpointHitDto endpointHitDto);

    List<ViewStats> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
