package com.yannick.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yannick.dto.HomeDto;
import com.yannick.repository.PreferencesRepository;
import com.yannick.response.MarsPhoto;
import com.yannick.response.MarsRoverApiResponse;

/* a service helps when multiple controlers have to do the same thing
it is good for code reuse (business logic can be put in service classes
You want to have as little as possible business logic in controlers,
it is better if the controler just listens for requests and calls a service to execute logic.

*/
@Service
public class MarsRoverApiService {
	private static String API_KEY = "G2Ou3KbYWxTv0Vqfu1Eb5x8o8NnIgX0xtZqnkRnl";
	
	private Map<String, List<String>> validCameras = new HashMap<>();
	
	@Autowired
	private PreferencesRepository preferencesRepo;
	
	public Map<String, List<String>> getValidCameras() {
		return validCameras;
	}

	public MarsRoverApiService() {
		//creator for when MarsRoverApiService is instantiated, it will populate validCameras with the allowed cameras per rover
		validCameras.put("Opportunity", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
		validCameras.put("Curiosity", Arrays.asList("FHAZ","RHAZ","NAVCAM","CHEMCAM","MAHLI","MARDI","MAST"));
		validCameras.put("Spirit", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
	}
	
	public MarsRoverApiResponse getRoverData(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		RestTemplate rt = new RestTemplate();
		

		List<String> apiUrlEndpoints = getApiUrlEndpoints(homeDto);
		List<MarsPhoto> photos = new ArrayList<>();
		MarsRoverApiResponse response = new MarsRoverApiResponse();
		
		apiUrlEndpoints.stream()
					   .forEach(url -> {
						 MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
						 photos.addAll(apiResponse.getPhotos());
					   });
			
			
		response.setPhotos(photos);
	//	System.out.println(response.getBody());
		return response;
	}
	
	public List<String> getApiUrlEndpoints(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//builds a list of all urls we need to call
		List<String> urls = new ArrayList<>();
		
		
		/* We will get a lot of methods from this class, which we will use to split them to get the Camera names per rover
		 * Allowing us to build the request queries per camera and per rover, so we dont need 9 If's
		 * */
		 
		Method[] methods = homeDto.getClass().getMethods(); //gets all methods of homeDto
		
		/*
		 * Explain what the code does
		 * this code will grab all getCamera* methods and if value returns True than we will buikd a API Url to call in order to fetch pictures
		 * for a given rover / camera / sol.
		 * 
		 * we use Java Reflection
		 */
		
		for (Method method : methods) {
			//loop over all methods to find one for each camera, choose getters or setters
			if(method.getName().indexOf("getCamera")>-1 && Boolean.TRUE.equals(method.invoke(homeDto) )) { // check if it exists and if this camera is in the request
				//  Boolean.TRUE.equals(method.invoke(homeDto) => method bevat een methode, bijvoorbeeld "getCameraFHAZ", en die willen we
				// nu aanroepen (Invoke) op een specifiek object (homeDto), hij roept de getter aan, dus zal true of false terug krijgen.
				//
				// we want to split
				String cameraName = method.getName().split("getCamera")[1].toUpperCase();//this is prone for nullPointer exceptions
				//now check if the camera is valid for the particular rover.
				if(validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) { // Yes for this rover the camera is valid
					urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera="+cameraName);
					
				}
			};
		}
		
		/*
		if(Boolean.TRUE.equals(homeDto.getCameraFhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=FHAZ");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraChemcam()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=CHEMCAM");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraMahli()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=MAHLI");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraMardi()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=MARDI");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraMast()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=MAST");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraMinites()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=MINITES");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraNavcam())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=NAVCAM");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraPancam()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=PANCAM");
		}
		if(Boolean.TRUE.equals(homeDto.getCameraRhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ homeDto.getMarsApiRoverData() +"/photos?sol=" + homeDto.getMarsSol() + " &api_key="+API_KEY+"&camera=RHAZ");
		}*/
		return urls;
	}

	public HomeDto save(HomeDto homeDto) {
		preferencesRepo.save(homeDto);
		return homeDto;
		
	}

	public HomeDto findByUserId(Long userId) {
		return preferencesRepo.findByUserId(userId);
		
		
	}
}
