package com.surobinda.spring.data.jpa.domain.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.surobinda.spring.data.jpa.domain.entity.Movie;

@Repository
public interface IMovieDAO extends CrudRepository<Movie, Long>
{

}
