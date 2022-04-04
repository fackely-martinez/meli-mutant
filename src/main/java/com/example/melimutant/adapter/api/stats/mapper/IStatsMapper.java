package com.example.melimutant.adapter.api.stats.mapper;

import com.example.melimutant.adapter.api.stats.contract.Response;
import com.example.melimutant.core.person.domain.PersonStatsDomain;

public interface IStatsMapper {
    Response domainToResponse(PersonStatsDomain personStatsDomain);
}
