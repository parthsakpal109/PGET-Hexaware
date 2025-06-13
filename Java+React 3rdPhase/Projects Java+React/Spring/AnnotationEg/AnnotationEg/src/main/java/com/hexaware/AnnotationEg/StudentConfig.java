package com.hexaware.AnnotationEg;

import org.springframework.context.annotation.Bean;

public class StudentConfig {
	@Bean(name="s1")
	public Student getStudent() {
		Student s = new Student();
		s.setRoll(101);
		s.setName("Parth");
		s.setFee(20000.0);
		return s;
	}
	
	@Bean(name="c1")
	public Collage getCollage() {
		Collage c = new Collage();
		c.setC_id(201);
		c.setC_nm("MIT");
		return c;
	}
}