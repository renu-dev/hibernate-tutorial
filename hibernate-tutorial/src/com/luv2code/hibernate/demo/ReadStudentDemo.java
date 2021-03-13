package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			Student tStudent = new Student("Daffy","Doe","daffy@gmail.com");
			System.out.println(tStudent);
			
			//begin or start transaction
			session.beginTransaction();
			
			//save student object to database
			System.out.println("Saving the object");
			session.save(tStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
            //My New Code
			//retrieving 
			//find student's id: primary key
			System.out.println("Saved student: generated id"+ tStudent.getId());
			
			//now get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on primary key
			System.out.println("getting student with id: "+ tStudent.getId());
			Student myStudent = session.get(Student.class, tStudent.getId());
			System.out.println("get complete: "+ myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
	}

}
