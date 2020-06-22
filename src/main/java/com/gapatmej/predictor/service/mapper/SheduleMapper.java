package com.gapatmej.predictor.service.mapper;

import com.gapatmej.predictor.domain.Schedule;
import com.gapatmej.predictor.service.dto.ScheduleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity Restriction and its DTO RestrictionDTO.
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SheduleMapper extends EntityMapper<ScheduleDTO, Schedule> {

}
