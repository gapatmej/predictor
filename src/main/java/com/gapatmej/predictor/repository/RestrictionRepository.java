package com.gapatmej.predictor.repository;

import com.gapatmej.predictor.domain.Restriction;
import com.gapatmej.predictor.domain.enumeration.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Long> {

  @Query(" SELECT r FROM Restriction r WHERE r.plateNumber =:plateNumber and r.day = :day " +
    " AND :hours >= r.schedule.fromHour AND :hours <= r.schedule.toHour" )
  List<Restriction> getRestrictions(@Param("plateNumber") int plateNumber, @Param("day") Day day, @Param("hours") int hours);
}
