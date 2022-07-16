package com.bezkoder.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String url;

	private Long movie;

	public Image(String url, Long movie) {
		this.url = url;
		this.movie = movie;
	}

	public Image() {

		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getMovie() {
		return movie;
	}

	public void setMovie(Long movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", url=" + url + ", movie=" + movie + "]";
	}
}
