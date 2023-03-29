package io.spring.api;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import io.spring.api.models.Region;
import io.spring.api.services.RegionService;

@SpringBootTest
class ApiApplicationTests {
	
	private TestEntityManager entityManager;

	@Autowired
	private RegionService regionService;

	@Test
	void contextLoads() {
	}

	@Test
	void insert(){
		//arrange
		Region region = new Region();
		region.setName("Waktu Test");

		//act
		Boolean result = regionService.save(region);
		
		//assert
		Assertions.assertThat(result).isEqualTo(true);
	}
	@Test
	void update(){
		//arrange
		Region region = regionService.Get(26);
		region.setName("Waktu Test2");

		//act
		Boolean result = regionService.save(region);
		
		//assert
		Assertions.assertThat(result).isEqualTo(true);	
	}

	@Test
	void GetAll(){
		//arrange
		List<Region> regions = regionService.Get();

		//act
		Boolean results = regions.isEmpty();
		//assert
		Assertions.assertThat(results).isEqualTo(false);
	}
	@Test
	void GetById(){
		//arrange
		Region regions = regionService.Get(1);

		//act
		
		//assert
		Assertions.assertThat(regions).isNotNull();
	}

	@Test
	void delete(){
		Boolean results = regionService.delete(26);

		Assertions.assertThat(results).isEqualTo(true);
	}

}
