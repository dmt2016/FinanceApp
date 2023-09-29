package com.Finance.FinApp.models.credit;

import com.Finance.FinApp.service.CreditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    @Mock
    private CreditRepo creditRepo;

    @InjectMocks
    private CreditService creditService;

    private Credit credit;

    @BeforeEach
    public void setup(){
        credit = Credit.builder()
                .id(5L)
                .date(LocalDate.of(2022,5, 20))
                .company("Target")
                .amount(15.99)
                .category(Category.GIFT)
                .build();
    }

    @Test
    public void creditCreateTest(){
        //given(creditRepo.findById(credit.getId())).willReturn(Optional.empty());
        given(creditRepo.save(credit)).willReturn(credit);

        Credit savedCredit = creditService.createCredit(credit);
        assertThat(savedCredit).isNotNull();
    }

    @Test
    public void getCreditByIdTest(){
        given(creditRepo.findById(credit.getId())).willReturn(Optional.of(credit));
        Credit savedCredit = creditService.getCreditById(credit.getId());
        assertThat(savedCredit).isEqualTo(credit);

    }

    @Test
    public void deleteCreditTest(){
        Long creditId = 5L;
        willDoNothing().given(creditRepo).deleteById(creditId);
        creditService.deleteCredit(creditId);
        verify(creditRepo, times(1)).deleteById(creditId);
    }

    @Test
    public void updateCreditTest(){
        given(creditRepo.save(credit)).willReturn(credit);
        credit.setCompany("Walmart");
        credit.setAmount(20.00);
        // when -  action or the behaviour that we are going test
        Credit updatedCredit = creditService.updateCredit(credit);

        // then - verify the output
        assertThat(updatedCredit.getCompany()).isEqualTo("Walmart");
        assertThat(updatedCredit.getAmount()).isEqualTo(20.00);
    }

    @Test
    public void getAllCreditsTest(){

        Credit credit1 = Credit.builder()
                .id(3L)
                .date(LocalDate.of(2022,1, 25))
                .company("Kohls")
                .amount(2.99)
                .category(Category.REFUND)
                .build();

        given(creditRepo.findAll()).willReturn(List.of(credit, credit1));

        // when -  action or the behaviour that we are going test
        List<Credit> creditsList = creditService.getCredits();

        // then - verify the output
        assertThat(creditsList).isNotNull();
        assertThat(creditsList.size()).isEqualTo(2);

    }

    @Test
    public void getYTDCredsTest(){

        Credit credit1 = Credit.builder()
                .id(3L)
                .date(LocalDate.of(2021,1, 25))
                .company("Kohls")
                .amount(2.99)
                .category(Category.REFUND)
                .build();

        int currYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(currYear, 1, 1);
        LocalDate endDate = LocalDate.of(currYear,12,31);


        given(creditRepo.findByDateBetween(startDate, endDate)).willReturn(List.of(credit));

        // when -  action or the behaviour that we are going test
        Double creditsYTDSum = creditService.getYTDCredits();

        // then - verify the output
        assertThat(creditsYTDSum).isNotNull();
        assertThat(creditsYTDSum).isEqualTo(15.99);
    }
}