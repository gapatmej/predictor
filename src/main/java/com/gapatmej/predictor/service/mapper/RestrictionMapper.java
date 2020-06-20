package com.gapatmej.predictor.service.mapper;

import com.gapatmej.predictor.domain.Restriction;
import com.gapatmej.predictor.domain.Schedule;
import com.gapatmej.predictor.service.dto.RestrictionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper for the entity Restriction and its DTO RestrictionDTO.
 */
@Mapper(componentModel = "spring", uses = {Schedule.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestrictionMapper extends EntityMapper<RestrictionDTO, Restriction> {

    default Restriction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Restriction restriction = new Restriction();
        restriction.setId(id);
        return restriction;
    }
}
