package com.yannick.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.yannick.dto.HomeDto;
import com.yannick.repository.PreferencesRepository;
import com.yannick.response.MarsRoverApiResponse;
import com.yannick.service.MarsRoverApiService;

@Controller
public class HomeController {
	
	@Autowired
	private MarsRoverApiService roverService;
	//Autowired means Spring will create and maintain the object
	@Autowired 
	private PreferencesRepository preferencesRepo;
	
	@GetMapping("/")
	public String getHomeView(ModelMap model, Long userId, boolean createUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HomeDto homeDto = createDefaultHomeDto(userId);
		
	    if (Boolean.TRUE.equals(createUser) && userId == null) {
	        homeDto = roverService.save(homeDto);
	      } else {
	        homeDto = roverService.findByUserId(userId);  
	        if (homeDto == null) {
	          homeDto = createDefaultHomeDto(userId);
	        }
	      }
		
		
		
		// set default value when request is empty
		if(StringUtils.isEmpty(homeDto.getMarsApiRoverData())) {
			
		}
		if(homeDto.getMarsSol() == null) {
			
		}
		MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
		model.put("roverData", roverData);
		model.put("homeDto", homeDto);
		model.put("validCameras",roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));
		System.out.println("homeDto before the save: "+homeDto);
	    if (!Boolean.TRUE.equals(homeDto.getRememberPreferences()) && userId != null) {
	        HomeDto defaultHomeDto = createDefaultHomeDto(userId);
	        roverService.save(defaultHomeDto);
	        // in this part there is a bug. Om een of andere reden stuurt hij de defaulteHomeDto toch naar de frontend en wordut die weergegeven als
	        // ik deze boventstaande Save uitvoer.
	        System.out.println("delfault: "+defaultHomeDto);
	      }
		
		//by putting things on the model, we communicate them to the frontend
	    System.out.println("homeDto: "+homeDto);
	    
		return "index";
	}

	  private HomeDto createDefaultHomeDto(Long userId) {
		    HomeDto homeDto = new HomeDto();
		    homeDto.setMarsApiRoverData("Curiosity");
		    homeDto.setMarsSol(10);
		    homeDto.setUserId(userId);
		    return homeDto;
		  }
	
	@PostMapping("/")
	public String postHomeView(HomeDto homeDto) {
		homeDto = roverService.save(homeDto);
		
		System.out.println(homeDto);
		
		return "redirect:/?userId="+homeDto.getUserId();
		
	}
	
	
}