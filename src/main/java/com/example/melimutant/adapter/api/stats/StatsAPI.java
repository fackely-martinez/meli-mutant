package com.example.melimutant.adapter.api.stats;

import com.example.melimutant.adapter.api.stats.contract.Response;
import com.example.melimutant.adapter.api.stats.mapper.IStatsMapper;
import com.example.melimutant.core.person.exception.NoContentException;
import com.example.melimutant.port.IPersonStatsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
public class StatsAPI {
    private static final Logger logger = LoggerFactory.getLogger(StatsAPI.class);

    private final IPersonStatsUseCase personStatsUseCase;
    private final IStatsMapper statsMapper;

    public StatsAPI(IPersonStatsUseCase personStatsUseCase,
                    IStatsMapper statsMapper) {
        this.personStatsUseCase = personStatsUseCase;
        this.statsMapper = statsMapper;
    }

    @GetMapping
    public ResponseEntity<Response> get() {
        try {
            return ResponseEntity.ok(statsMapper.domainToResponse(
                    personStatsUseCase.execute()
            ));
        } catch (NoContentException noContentException) {
            logger.error(noContentException.toString());
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            logger.error(exception.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
