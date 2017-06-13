package com.sky.vod;

import org.apache.log4j.Logger;

import com.sky.movie.metadata.MovieService;
import com.sky.movie.metadata.TechnicalFailureException;

public class DefaultParentalControlService implements ParentalControlService {

	private Logger logger = Logger.getLogger(DefaultParentalControlService.class); 

	private MovieService movieService;

	public DefaultParentalControlService(MovieService movieService) {
		this.movieService = movieService;
	}

	@Override
	public boolean isCustomerAbleToWatch(ParentalControlLevels customerParentalControlLevelPreference, String movieId) {
		
		if(customerParentalControlLevelPreference != null) {
			try {
				// get parental control level of the movie
				String parentalControlLevelStr = movieService.getParentalControlLevel(movieId);

				ParentalControlLevels parentalControlLevelOfTheMovie = ParentalControlLevels.fromString(parentalControlLevelStr);
				if (parentalControlLevelOfTheMovie != null) {
					//If the parental control level of the movie is equal to or less than the
					//customer’s preference indicate to the caller that the customer can
					//watch the movie
					if (parentalControlLevelOfTheMovie.isEqualToOrLessThan(customerParentalControlLevelPreference))
						return true;
				} else {
					logger.error("parentalControlLevelStr:" + parentalControlLevelStr + " not defined, valid values are: U, PG, 12, 15, 18");
				}
			} catch (TechnicalFailureException e) {
				logger.warn("the customer cannot watch this movie due to system error", e);
			}
		} else {
			logger.error("no customerPreference for ParentalControlLevel found!");
		}
		return false;
	}



}
