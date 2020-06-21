package com.gapatmej.predictor.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gapatmej.predictor.exception.PredictorException;
import com.gapatmej.predictor.service.RestrictionService;
import com.gapatmej.predictor.service.dto.RestrictionDTO;
import com.gapatmej.predictor.web.rest.error.BadRequestAlertException;
import com.gapatmej.predictor.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RestrictionResource {

  private final Logger log = LoggerFactory.getLogger(RestrictionResource.class);

  private final RestrictionService restrictionService;

  public RestrictionResource(RestrictionService restrictionService) {
    this.restrictionService = restrictionService;
  }

  /**
   * GET  /restriction : get one restriction .
   *
   * @param licensePlateNumber id of a car
   * @param date               to seacrh restriction
   * @param hours              to seacrh restriction
   * @param minutes            to seacrh restriction
   * @return the ResponseEntity with status 200 (OK) and the restriction in body
   */
  @GetMapping("/restriction/{licensePlateNumber}/{date}/{hours}/{minutes}")
  public ResponseEntity<List<RestrictionDTO>> getRestriction(@PathVariable String licensePlateNumber, @PathVariable String date, @PathVariable int hours, @PathVariable int minutes) {
    try {
      log.debug(String.format("REST request to get a page of Subscriptions: licensePlateNumber:%s, " +
        "date:%s, hours:%d, minutes:%d ", licensePlateNumber, date, hours, minutes));
      Optional<List<RestrictionDTO>> restrictions = restrictionService.findRestrictions(licensePlateNumber, date, hours, minutes);
      return ResponseUtil.wrapOrNotFoundList(restrictions);
    } catch (PredictorException e) {
      throw new BadRequestAlertException(e.getMessage(),"ENTITY_NAME", e.getKeyMessage());
    }

  }

}
