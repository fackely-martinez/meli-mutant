package com.example.melimutant.port;

import com.example.melimutant.core.person.command.RatioCommand;

public interface IPersonStatsService {
    double calculateRatio(RatioCommand ratioCommand);
}
