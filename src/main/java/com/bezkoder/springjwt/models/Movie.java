package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Long age;

	private Float vote;

	private String introduce;

	private String date;

	private Long turmover;

	private Long time;

	private String key_youtube;

	private String name_youtube;

	private String url;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_category", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categorys = new HashSet<>();

	public Movie() {

		// TODO Auto-generated constructor stub
	}

	public Movie(String name, Long age, Float vote, String introduce, String date, Long turmover, Long time,
			String key_youtube, String name_youtube, String url, Set<Category> categorys) {
		this.name = name;
		this.age = age;
		this.vote = vote;
		this.introduce = introduce;
		this.date = date;
		this.turmover = turmover;
		this.time = time;
		this.key_youtube = key_youtube;
		this.name_youtube = name_youtube;
		this.url = url;
		this.categorys = categorys;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Float getVote() {
		return vote;
	}

	public void setVote(Float vote) {
		this.vote = vote;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getTurmover() {
		return turmover;
	}

	public void setTurmover(Long turmover) {
		this.turmover = turmover;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

	public String getKey_youtube() {
		return key_youtube;
	}

	public void setKey_youtube(String key_youtube) {
		this.key_youtube = key_youtube;
	}

	public String getName_youtube() {
		return name_youtube;
	}

	public void setName_youtube(String name_youtube) {
		this.name_youtube = name_youtube;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", age=" + age + ", vote=" + vote + ", introduce=" + introduce
				+ ", date=" + date + ", turmover=" + turmover + ", time=" + time + ", key_youtube=" + key_youtube
				+ ", name_youtube=" + name_youtube + ", url=" + url + ", categorys=" + categorys + "]";
	}

}
