package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure()
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			//now get new session and start transaction
			session = factory.getCurrentSession();
			
            //My New Code
			//updating 
			session.beginTransaction();
			
			//retrieve student based on primary key
			System.out.println("getting student with id: "+ studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("updating student: "+ myStudent);			
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//New Code to update all student email ids
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("updating email for all students");
			session.createQuery("update Student s set s.email='foo@gmail.com'").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
	}

}
