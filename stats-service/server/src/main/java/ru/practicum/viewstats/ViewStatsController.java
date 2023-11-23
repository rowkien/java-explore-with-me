package ru.practicum.viewstats;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.EndpointHitDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ViewStatsController {

    private final ViewStatsService viewStatsService;

    @PostMapping("/hit")
    public EndpointHitDto saveHit(@RequestBody EndpointHitDto endpointHitDto) {
        return viewStatsService.saveHit(endpointHitDto);
    }

    @GetMapping("/stats")
    public List<ViewStats> getViewStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                        @RequestParam(required = false) List<String> uris,
                                        @RequestParam(value = "unique", defaultValue = "false") boolean unique) {
        return viewStatsService.getViewStats(start, end, uris, unique);
    }

}
