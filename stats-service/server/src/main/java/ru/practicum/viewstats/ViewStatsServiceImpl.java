package ru.practicum.viewstats;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.EndpointHitDto;
import ru.practicum.mappers.EndpointHitMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewStatsServiceImpl implements ViewStatsService {
    private final ViewStatsRepository viewStatsRepository;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto endpointHitDto) {
        return EndpointHitMapper.hitToDto(viewStatsRepository.save(EndpointHitMapper.toHit(endpointHitDto)));
    }

    @Override
    public List<ViewStats> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<ViewStats> result;
        if (unique) {
            result = viewStatsRepository.getViewStatsUnique(start, end, uris);
        } else {
            result = viewStatsRepository.getViewStatsAll(start, end, uris);
        }
        return result;
    }
}
