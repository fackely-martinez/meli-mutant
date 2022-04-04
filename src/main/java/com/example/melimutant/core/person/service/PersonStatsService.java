package com.example.melimutant.core.person.service;

import com.example.melimutant.core.person.command.RatioCommand;
import com.example.melimutant.port.IPersonStatsService;
import org.springframework.stereotype.Service;

@Service
public class PersonStatsService implements IPersonStatsService {
    @Override
    public double calculateRatio(RatioCommand ratioCommand) {
        if (ratioCommand.getTotalHumans() == 0){
            return 0;
        }
        return ratioCommand.getTotalMutants() / ratioCommand.getTotalHumans();
    }
}
