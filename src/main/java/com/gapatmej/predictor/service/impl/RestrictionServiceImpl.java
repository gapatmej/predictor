package com.gapatmej.predictor.service.impl;

import com.gapatmej.predictor.domain.Restriction;
import com.gapatmej.predictor.domain.enumeration.Day;
import com.gapatmej.predictor.exception.PredictorException;
import com.gapatmej.predictor.repository.RestrictionRepository;
import com.gapatmej.predictor.service.RestrictionService;
import com.gapatmej.predictor.service.dto.RestrictionDTO;
import com.gapatmej.predictor.service.mapper.RestrictionMapper;
import com.gapatmej.predictor.utils.Utils;
import com.gapatmej.predictor.web.rest.RestrictionResource;
import com.gapatmej.predictor.web.rest.error.ErrorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class RestrictionServiceImpl implements RestrictionService {

  private final Logger log = LoggerFactory.getLogger(RestrictionResource.class);
  private final RestrictionRepository restrictionRepository;
  private final RestrictionMapper restrictionMapper;

  public RestrictionServiceImpl(RestrictionRepository restrictionRepository,
                                RestrictionMapper restrictionMapper) {
    this.restrictionRepository = restrictionRepository;
    this.restrictionMapper = restrictionMapper;

  }

  @Override
  public Optional<List<RestrictionDTO>> findRestrictions(String licensePlateNumber, String date, int hours, int minutes) throws PredictorException {

    Pattern pattern = Pattern.compile(Utils.PATTERN_PLATE_NUMBER);
    Matcher matcher = pattern.matcher(licensePlateNumber);
    if (!matcher.matches()) {
      throw new PredictorException("aaaa", ErrorConstants.PLATE_NUMBER_INCORRECT);
    }

    Date dateFormat = null;
    try {
      DateFormat format = new SimpleDateFormat(Utils.DATE_FORMAT_DDMMYYYY, Locale.ENGLISH);
      format.setLenient(false);
      dateFormat = format.parse(date);
    } catch (ParseException e) {
      log.error(e.getMessage());
      throw new PredictorException(ErrorConstants.DATE_INCORRECT);
    }

    if (hours < 0 || hours > 23) {
      throw new PredictorException(ErrorConstants.HOUR_INCORRECT);
    }

    if (minutes < 0 || minutes > 59) {
      throw new PredictorException(ErrorConstants.MINUTE_INCORRECT);
    }

    int numberPlate = Integer.parseInt(licensePlateNumber.substring(licensePlateNumber.length() - 1));
    SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
    Day day = Day.get(simpleDateformat.format(dateFormat));

    Optional<List<Restriction>> restrictions = restrictionRepository.getRestrictions(numberPlate, day, hours, minutes);
    return restrictions.map(restrictionMapper::toDto);
  }

}
