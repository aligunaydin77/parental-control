package com.sky.movie.metadata;

public class TitleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The movie service could not find the given movie";
	}
}
