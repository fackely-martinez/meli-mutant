package com.example.melimutant.adapter.api.stats.mapper.impl;

import com.example.melimutant.adapter.api.stats.contract.Response;
import com.example.melimutant.adapter.api.stats.mapper.IStatsMapper;
import com.example.melimutant.core.person.domain.PersonStatsDomain;
import org.springframework.stereotype.Service;

@Service
public class StatsMapper implements IStatsMapper {
    @Override
    public Response domainToResponse(PersonStatsDomain personStatsDomain) {
        return Response.builder()
                .countMutantDNA(personStatsDomain.getCountMutantDNA())
                .countHumanDNA(personStatsDomain.getCountHumanDNA())
                .ratio(personStatsDomain.getRatio())
                .build();
    }
}
