package com.surobinda.spring.data.jpa.api.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surobinda.spring.data.jpa.business.service.IMovieBusinessService;
import com.surobinda.spring.data.jpa.domain.entity.Movie;


@Service
@Path("api/movie")
public class MovieServiceRESTImpl 
{

	@Autowired
	IMovieBusinessService MovieBusinessService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<Movie> getMovieList()
	{
		List<Movie> movies = MovieBusinessService.getMovieList();
		System.out.println("List of Movies collection: " + movies);
		return movies;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Movie createMovie(Movie movie)
	{
		movie = MovieBusinessService.createMovie(movie);
		//Now change the movie object to check if it is still related to an open session
		//movie.setMovieName("After save test II");
		
		System.out.println("Generated movie id: " + movie.getMovieId());
		
		//Following transaction test will roll back the entire transaction
		//someExceptionalMessage();
		return movie;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Movie updateMovie(Movie movie)
	{
		return MovieBusinessService.updateMovie(movie);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@Path("/{movieId}")
	public Movie getMovieById(@PathParam("movieId") long movieId)
	{
		return MovieBusinessService.getMovieByMovieId(movieId);
	}
	
	private void someExceptionalMessage()
	{
		throw new RuntimeException("Transaction test exception");
	}
}
