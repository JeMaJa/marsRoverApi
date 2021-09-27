package com.yannick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yannick.dto.HomeDto;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long> {
	/* starting with findBy will tell JPA in Spring that 
	 * this will be a select * query, how cool!
	 * */
	// below annotation is commented out, it is a reference on how to create custom queriesList<HomeDto> findByCameraChemcam(Boolean cameraChemcam);
	//@Query("select dto from HomeDto dto where userId = :userId")
	//@Query(value="select * from mars_api_preferences where user_id = :userId", nativeQuery=true)
	HomeDto findByUserId(Long userId);
	
}
