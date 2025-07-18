package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pcs.tradingapp.exceptions.BidListNotFoundException;
import com.pcs.tradingapp.repositories.BidListRepository;
import com.pcs.tradingapp.services.bidlist.BidListService;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {
	@Mock
	private BidListRepository repository;
	
	@InjectMocks
	private BidListService service;
	
	@Test
	void testFetchBidList_withUnknownId_shouldThrowException() {
	    when(repository.findById(999)).thenReturn(Optional.empty());

	    assertThrows(BidListNotFoundException.class, () -> {
	        service.fetchUpdateBidListDtoById(999);
	    });
	}
}
