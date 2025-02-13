package com.example.cinema.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cinema.model.MovieEntity;
import com.example.cinema.repository.MovieRepository;



@Controller
@RequestMapping("/")
public class MovieController {
	
	@Autowired
	private MovieRepository movieListRepo;

	@RequestMapping(method=RequestMethod.GET, value="/movies/{actor}")
	public String getMovieListByActor(@PathVariable("actor")String name, Model mod) {
		
		/*List<MovieEntity> movieList = new ArrayList<MovieEntity>();
		MovieEntity movieEnt = new MovieEntity();
		movieEnt.setActor("tom");
		movieEnt.setDescription("Mission Imposible");
		movieEnt.setName("MI3");
		
		movieList.add(movieEnt);*/
		
		List<MovieEntity> movieList = movieListRepo.findMoviesByActor(name);
		
		mod.addAttribute("mov",movieList);
		
		return "movieList";
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/movies", consumes="application/json")
	public ResponseEntity<Object> addMovie(@RequestBody MovieEntity movie) {
		movieListRepo.save(movie);
		return ResponseEntity.ok().build() ;
	}
	
	
	
	
}
