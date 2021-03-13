package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			//deleting 
			session.beginTransaction();
			
			//retrieve student based on primary key
			System.out.println("getting student with id: "+ studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("deleting student: "+ myStudent);
			//session.delete(myStudent);
			//OR
   		    session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
	}

}
