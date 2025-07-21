package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pcs.tradingapp.dto.request.trade.UpdateTradeDto;
import com.pcs.tradingapp.exceptions.TradeNotFoundException;
import com.pcs.tradingapp.repositories.TradeRepository;
import com.pcs.tradingapp.services.trade.TradeService;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
	@Mock
	private TradeRepository repository;
	
	@InjectMocks
	private TradeService service;
	
    @Test
    public void testUpdateTradeDtoShouldThrowException() {   	
        UpdateTradeDto dto = new UpdateTradeDto();

        when(repository.findById(0)).thenReturn(Optional.empty());

        assertThrows(TradeNotFoundException.class, () -> service.updateTrade(dto));
    }
}
