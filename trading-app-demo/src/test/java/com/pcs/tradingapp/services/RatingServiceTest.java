package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pcs.tradingapp.exceptions.RatingNotFoundException;
import com.pcs.tradingapp.repositories.RatingRepository;
import com.pcs.tradingapp.services.rating.RatingService;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
	@Mock
	private RatingRepository repository;
	
	@InjectMocks
	private RatingService service;
	
	@Test
	void testFetchRating_withUnknownId_shouldThrowException() {
	    when(repository.findById(999)).thenReturn(Optional.empty());

	    assertThrows(RatingNotFoundException.class, () -> {
	        service.getRatingById(999);
	    });
	}
}
