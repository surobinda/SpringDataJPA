package com.surobinda.spring.data.jpa.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="MOVIE")
public class Movie implements Serializable
{
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	@Column(name="MOVIE_ID")
	private long movieId;
	
	@Column(name="MOVIE_NAME")
	private String movieName;
	
	@Column(name="DURATION")
	private double duration;
	
	@Version
	private long version;

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
