package com.gapatmej.predictor.service.impl;

import com.gapatmej.predictor.domain.Restriction;
import com.gapatmej.predictor.domain.enumeration.Day;
import com.gapatmej.predictor.exception.PredictorException;
import com.gapatmej.predictor.repository.RestrictionRepository;
import com.gapatmej.predictor.service.RestrictionService;
import com.gapatmej.predictor.service.dto.RestrictionDTO;
import com.gapatmej.predictor.service.mapper.RestrictionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RestrictionServiceImpl implements RestrictionService {

    private final RestrictionRepository restrictionRepository;
    private final RestrictionMapper restrictionMapper;

    public RestrictionServiceImpl(RestrictionRepository restrictionRepository,
                                  RestrictionMapper restrictionMapper){
        this.restrictionRepository = restrictionRepository;
        this.restrictionMapper = restrictionMapper;
    }

    @Override
    public Optional<List<RestrictionDTO>> findRestrictions(String licensePlateNumber, String day, int hours, int minutes) throws PredictorException {

        Day s = Day.get(day);
        Optional<List<Restriction>> restrictions = restrictionRepository.getRestrictions(1,s,hours,minutes);
        return restrictions.map(restrictionMapper::toDto);
    }
}
