package ru.practicum.viewstats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.endpointhit.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface ViewStatsRepository extends JpaRepository<EndpointHit, Integer> {

    @Query(value = "select new ru.practicum.viewstats.ViewStats(e.app, e.uri, count(distinct e.ip)) " +
            "FROM EndpointHit e " +
            "WHERE e.timestamp between :start and :end " +
            "AND ((:uris) is null or e.uri in :uris) " +
            "GROUP BY e.ip, e.app, e.uri " +
            "ORDER BY count(distinct e.ip) desc")
    List<ViewStats> getViewStatsUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "select new ru.practicum.viewstats.ViewStats(e.app, e.uri, count(e.ip)) " +
            "FROM EndpointHit e " +
            "WHERE e.timestamp between :start and :end " +
            "AND ((:uris) is null or e.uri in :uris) " +
            "GROUP BY e.ip, e.app, e.uri " +
            "ORDER BY count(e.ip) desc")
    List<ViewStats> getViewStatsAll(LocalDateTime start, LocalDateTime end, List<String> uris);

}
