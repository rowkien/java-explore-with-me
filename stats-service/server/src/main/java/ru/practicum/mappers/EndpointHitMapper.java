package ru.practicum.mappers;

import ru.practicum.EndpointHitDto;
import ru.practicum.endpointhit.EndpointHit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndpointHitMapper {

    public static EndpointHitDto hitToDto(EndpointHit endpointHit) {
        return EndpointHitDto
                .builder()
                .app(endpointHit.getApp())
                .uri(endpointHit.getUri())
                .ip(endpointHit.getIp())
                .timestamp(endpointHit.getTimestamp().toString())
                .build();
    }

    public static EndpointHit toHit(EndpointHitDto endpointHitDto) {
        String str = endpointHitDto.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return EndpointHit
                .builder()
                .app(endpointHitDto.getApp())
                .uri(endpointHitDto.getUri())
                .ip(endpointHitDto.getIp())
                .timestamp(dateTime)
                .build();
    }

}
