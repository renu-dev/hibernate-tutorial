package com.luv2code.hibernate.demo;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure()
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {

			//create 3 student object
			System.out.println("Creating 3 new student objects");
			Student t1Student = new Student("John","Doe","john@gmail.com");
			Student t2Student = new Student("Mary","Public	","mary@gmail.com");
			Student t3Student = new Student("Damon","Salvatore","damon@gmail.com");
			
			//begin or start transaction
			session.beginTransaction();
			
			//save student object to database
			System.out.println("Saving the students");
			session.save(t1Student);
			session.save(t2Student);
			session.save(t3Student);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}finally {
			session.close();
		}
	}

}
