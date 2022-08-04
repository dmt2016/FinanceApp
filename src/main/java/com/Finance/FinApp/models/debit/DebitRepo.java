package com.Finance.FinApp.models.debit;

import com.Finance.FinApp.models.credit.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DebitRepo extends JpaRepository<Debit, Long> {
    List<Debit> findByDateBetween(LocalDate start, LocalDate end);
}
