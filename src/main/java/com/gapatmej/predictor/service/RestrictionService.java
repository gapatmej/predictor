package com.gapatmej.predictor.service;

import com.gapatmej.predictor.exception.PredictorException;
import com.gapatmej.predictor.service.dto.RestrictionDTO;

import java.util.List;
import java.util.Optional;


public interface RestrictionService {
    Optional<List<RestrictionDTO>> findRestrictions (String licensePlateNumber, String day, int hours, int minutes) throws PredictorException;
}
