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

				Course course = new Course();
				course.setName("Angular com Spring");
				course.setCategory(Category.FULL_STACK);

				Lesson lesson = new Lesson();
				lesson.setName("Introdução");
				lesson.setYoutubeUrl("https://www.youtube.com/watch?v=Nb4uxLxdvxo");
				lesson.setCourse(course);
				course.getLessons().add(lesson);

                Lesson lesson1 = new Lesson();
                lesson1.setName("Angular");
                lesson1.setYoutubeUrl("https://www.youtube.com/watch?v=Nb4uxLxdvxo");
                lesson1.setCourse(course);
                course.getLessons().add(lesson1);


			courseRepository.save(course);
		};
	}


}
