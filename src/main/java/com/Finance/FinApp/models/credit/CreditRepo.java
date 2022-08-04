package com.Finance.FinApp.models.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditRepo extends JpaRepository<Credit, Long> {
    List<Credit> findByDateBetween(LocalDate start, LocalDate end);

}
