package com.gapatmej.predictor.web.rest;

import com.gapatmej.predictor.PredictorApplication;
import com.gapatmej.predictor.domain.Restriction;
import com.gapatmej.predictor.domain.enumeration.Day;
import com.gapatmej.predictor.exception.PredictorException;
import com.gapatmej.predictor.service.RestrictionService;
import com.gapatmej.predictor.service.dto.RestrictionDTO;
import com.gapatmej.predictor.web.rest.error.BadRequestAlertException;
import com.gapatmej.predictor.web.rest.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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

    private final RestrictionService restrictionService;

    public RestrictionResource(RestrictionService restrictionService){
        this.restrictionService = restrictionService;
    }

    /**
     * GET  /restriction : get one restriction .
     *
     * @param licensePlateNumber id of a car
     * @param day to seacrh restriction
     * @param hours to seacrh restriction
     * @param minutes to seacrh restriction
     * @return the ResponseEntity with status 200 (OK) and the restriction in body
     */
    @GetMapping("/restriction/{licensePlateNumber}/{day}/{hours}/{minutes}")
    public ResponseEntity<List<RestrictionDTO>> getRestriction(@PathVariable String licensePlateNumber, @PathVariable String day, @PathVariable int hours, @PathVariable int minutes) {
        try{
            //log.debug("REST request to get a page of Subscriptions");
            Optional<List<RestrictionDTO>> restrictions = restrictionService.findRestrictions(licensePlateNumber,day,hours,minutes);
            return ResponseUtil.wrapOrNotFoundList(restrictions);
        }catch (PredictorException e ){
            throw new BadRequestAlertException(e.getMessage(),"ENTITY_NAME", e.getKeyMessage());
        }

    }
}
