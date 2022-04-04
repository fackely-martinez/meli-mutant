package com.example.melimutant.port;

import com.example.melimutant.core.person.domain.PersonStatsDomain;

public interface IPersonStatsUseCase {
    PersonStatsDomain execute() throws Exception;
}
