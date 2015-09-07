package com.surobinda.spring.data.jpa.business.service;

import java.util.List;

import com.surobinda.spring.data.jpa.domain.entity.Movie;

public interface IMovieBusinessService 
{
	public List<Movie> getMovieList();
	
	public Movie createMovie(Movie movie);
	
	public Movie getMovieByMovieId(long movieId);
	
	public Movie updateMovie(Movie movie);
}
