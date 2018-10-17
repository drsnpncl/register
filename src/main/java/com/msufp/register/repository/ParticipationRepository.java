package com.msufp.register.repository;

import com.msufp.register.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findByEventId(Long id);

    List<Participation> findByRegDate(Date date);

    List<Participation> findByPaymentMode(String mode);

    List<Participation> findByPaymentAccept(Boolean paymentAccept);

    List<Participation> findByAbstractSelect(Boolean abstractSelect);

    Optional<Participation> findByGroupId(Long id);

    Optional<Participation> findById(Long id);
}
