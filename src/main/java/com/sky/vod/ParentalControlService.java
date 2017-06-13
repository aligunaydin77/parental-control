package com.sky.vod;


public interface ParentalControlService {
	 boolean isCustomerAbleToWatch(ParentalControlLevels parentalContolPreference, String movieId);
}
