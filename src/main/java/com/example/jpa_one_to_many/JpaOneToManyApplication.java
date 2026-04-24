package com.example.jpa_one_to_many;

import com.example.jpa_one_to_many.dao.AppDAO;
import com.example.jpa_one_to_many.entity.Course;
import com.example.jpa_one_to_many.entity.Instructor;
import com.example.jpa_one_to_many.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaOneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			createInstructorWithCourses(appDAO);
		};
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//create instructor
		System.out.println("Creating Instructor...");
		Instructor theInstructor=new Instructor("Katy","Perry","lol@gmail.com");

		//create instructor details
		System.out.println("Creating InstructorDetails...");
		InstructorDetail theDetails=new InstructorDetail("DiscoveryChannel","painting");

		//associate the objects
		theInstructor.setInstructorDetail(theDetails);

		//create courses
		Course tempCourse1=new Course("EMF");
		Course tempCourse2=new Course("SNS");
		Course tempCourse3=new Course("HSS");

		theInstructor.add(tempCourse1);
		theInstructor.add(tempCourse2);
		theInstructor.add(tempCourse3);

		//save the instructor
		appDAO.save(theInstructor);


	}

}
