package com.sky.vod.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sky.movie.metadata.MovieService;
import com.sky.movie.metadata.TechnicalFailureException;
import com.sky.movie.metadata.TitleNotFoundException;
import com.sky.vod.DefaultParentalControlService;
import com.sky.vod.ParentalControlLevels;
import com.sky.vod.ParentalControlService;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {
	
	private String movieId = "Singin' in the Rain";
	
	@Mock
	private MovieService movieService;
	
	@InjectMocks
	private ParentalControlService parentalControlService= new DefaultParentalControlService(movieService);
	
	
	@Test
	public void testParentalControlLevel() throws TitleNotFoundException, TechnicalFailureException  {
		
		ParentalControlLevels customerPreference = ParentalControlLevels.PCL_12;
		when(movieService.getParentalControlLevel(movieId)).thenReturn("PG");
		assertTrue(parentalControlService.isCustomerAbleToWatch(customerPreference, movieId));
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn("U");
		assertTrue(parentalControlService.isCustomerAbleToWatch(customerPreference, movieId));
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn("12");
		assertTrue(parentalControlService.isCustomerAbleToWatch(customerPreference, movieId));
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn("15");
		assertFalse(parentalControlService.isCustomerAbleToWatch(customerPreference, movieId));
		
		when(movieService.getParentalControlLevel(movieId)).thenReturn("18");
		assertFalse(parentalControlService.isCustomerAbleToWatch(customerPreference, movieId));
		
	}
	
	@Test(expected=TitleNotFoundException.class)
	public void testTitleNotFoundException() throws TitleNotFoundException, TechnicalFailureException  {
		ParentalControlLevels parentalContolPreference = ParentalControlLevels.PCL_12;
		//The movie service could not find the given movie
		when(movieService.getParentalControlLevel(movieId)).thenThrow(new TitleNotFoundException());
		parentalControlService.isCustomerAbleToWatch(parentalContolPreference, movieId);
	}
	
	@Test
	public void testTechnicalFailureException() throws TitleNotFoundException, TechnicalFailureException  {
		ParentalControlLevels parentalContolPreference = ParentalControlLevels.PCL_12;
		when(movieService.getParentalControlLevel(movieId)).thenThrow(new TechnicalFailureException());
		assertFalse(parentalControlService.isCustomerAbleToWatch(parentalContolPreference, movieId));
	}
	
	@Test
	public void testEmptyCustomerPreference() throws TitleNotFoundException, TechnicalFailureException  {
		when(movieService.getParentalControlLevel(movieId)).thenReturn("PG");
		assertFalse(parentalControlService.isCustomerAbleToWatch(null, movieId));
	}
	
	@Test
	public void testUnfoundParentalControlLevel() throws TitleNotFoundException, TechnicalFailureException  {
		ParentalControlLevels parentalContolPreference = ParentalControlLevels.PCL_18;
		when(movieService.getParentalControlLevel(movieId)).thenReturn("AB");
		assertFalse(parentalControlService.isCustomerAbleToWatch(parentalContolPreference, movieId));
	}


}
