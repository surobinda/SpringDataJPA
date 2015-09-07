package com.surobinda.spring.data.jpa.business.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surobinda.spring.data.jpa.domain.dao.IMovieDAO;
import com.surobinda.spring.data.jpa.domain.entity.Movie;

@Service
public class MovieBusinessService implements IMovieBusinessService
{

	@Autowired
	IMovieDAO movieDAO;
	
	@Override
	@Transactional
	public List<Movie> getMovieList() 
	{
		List<Movie> movies = (List<Movie>) movieDAO.findAll();
		return movies;
	}

	@Override
	@Transactional
	public Movie createMovie(Movie movie) 
	{
		movie = movieDAO.save(movie);
		System.out.println("Generated movie id: " + movie.getMovieId());
		
		//Following transaction test will roll back the entire transaction
		//someExceptionalMessage();
		
		//Create another Movie
		Movie anotherMovie = new  Movie();
		anotherMovie.setMovieName("Another Movie - " + new Date());
		anotherMovie.setDuration(145);
		
		anotherMovie = movieDAO.save(anotherMovie);
		System.out.println("Generated movie id: " + anotherMovie.getMovieId());
		
		return movie;
	}
	
	private void someExceptionalMessage()
	{
		throw new RuntimeException("Transaction test exception");
	}
	
	@Transactional
	public Movie getMovieByMovieId(long movieId)
	{
		return movieDAO.findOne(movieId);
	}

	@Override
	@Transactional
	public Movie updateMovie(Movie movie) 
	{
		return movieDAO.save(movie);
	}

}
