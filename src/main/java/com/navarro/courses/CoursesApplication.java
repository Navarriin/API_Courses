package com.navarro.courses;

import com.navarro.courses.enums.Category;
import com.navarro.courses.model.Course;
import com.navarro.courses.model.Lesson;
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

				for(int i = 0; i < 20; i++) {

					Course course = new Course();
					course.setName("Angular com Spring" + i);
					course.setCategory(Category.FULL_STACK);

					Lesson lesson = new Lesson();
					lesson.setName("Introdução");
					lesson.setYoutubeUrl("watch?v=1");
					lesson.setCourse(course);
					course.getLessons().add(lesson);

					Lesson lesson1 = new Lesson();
					lesson1.setName("Angular");
					lesson1.setYoutubeUrl("watch?v=2");
					lesson1.setCourse(course);
					course.getLessons().add(lesson1);

					courseRepository.save(course);
				}
		};
	}


}
