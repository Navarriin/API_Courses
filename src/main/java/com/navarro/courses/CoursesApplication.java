package com.navarro.courses;

import com.navarro.courses.model.Course;
import com.navarro.courses.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

				Course course = new Course();
				course.setName("Angular");
				course.setCategory("front-end");

			courseRepository.save(course);
		};
	}


}
