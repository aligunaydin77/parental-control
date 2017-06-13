package com.sky.movie.metadata;

public interface MovieService {
	
	String getParentalControlLevel(String movieId)
			throws TitleNotFoundException, TechnicalFailureException;
}