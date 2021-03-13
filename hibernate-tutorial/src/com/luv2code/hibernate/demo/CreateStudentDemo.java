package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure()
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create student object
			System.out.println("Creating new student object");
			Student tStudent = new Student("Priyanka","Pal","priyanka@gmail.com");
			
			//begin or start transaction
			session.beginTransaction();
			
			//save student object to database
			System.out.println("Saving the object");
			session.save(tStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
	}

}
