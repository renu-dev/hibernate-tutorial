package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// begin or start transaction
			session.beginTransaction();

			// query students
			System.out.println("Retrieving all students");
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(theStudents);

			// query students : lastName='Doe'
			System.out.println("Retrieving students whose lastName='Doe'");
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

			displayStudents(theStudents);

			// query students : lastName='Doe' OR firstName='Daffy'
			System.out.println("Retrieving students whose lastName='Doe' OR firstName='Daffy'");
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
					.getResultList();

			displayStudents(theStudents);

			// query students : where email like '%gmail.com'
			System.out.println("Retrieving students whose email like '%gmail.com'");
			theStudents = session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();

			displayStudents(theStudents);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");

		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student temp : theStudents)
			System.out.println(temp);
	}

}
