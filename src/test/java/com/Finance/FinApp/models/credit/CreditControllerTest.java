package com.Finance.FinApp.models.credit;

import com.Finance.FinApp.controller.CreditController;
import com.Finance.FinApp.service.CreditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CreditControllerTest {
    @Mock
    CreditService creditService;
    private Credit credit;
    private List<Credit> creditList;

    @InjectMocks
    private CreditController creditController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        credit = new Credit(2L, LocalDate.of(2022,5,20), "Kohls", 20.99, Category.INCOME);
        mockMvc = MockMvcBuilders.standaloneSetup(creditController).build();
    }

    @AfterEach
    void tearDown(){
        credit = null;
    }

    @Test
    public void testPostCredit() throws Exception{
        when(creditService.createCredit(any())).thenReturn(credit);
        mockMvc.perform(post("/credit/").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(credit))).
                andExpect(status().isFound());
        verify(creditService,times(1)).createCredit(any());

    }

    @Test
    public void deleteCreditAPI() throws Exception {
        mockMvc.perform(get("/credit/delete/{id}", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    public void updateCreditAPI() throws Exception {
        mockMvc.perform(put("/credit/updated", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Credit(2L, LocalDate.of(2022,5,20), "Walmart", 15.99, Category.INCOME))))
                .andExpect(status().isFound());
        verify(creditService,times(1)).updateCredit(any());

/*        ArgumentCaptor<Credit> creditArgumentCaptor = ArgumentCaptor.forClass(Credit.class);
        verify(creditService, times(1)).updateCredit(creditArgumentCaptor.capture());
        assertThat(creditArgumentCaptor.getValue().getCompany()).isEqualTo("Walmart");
        assertThat(creditArgumentCaptor.getValue().getAmount()).isEqualTo(15.99);*/

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
