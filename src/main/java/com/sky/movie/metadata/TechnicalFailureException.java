package com.sky.movie.metadata;

public class TechnicalFailureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return  "Some technical fault";
	}
}
