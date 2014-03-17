package henry416.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import henry416.domain.Student;

public class StudentTest {

	private EntityManager manager;

	public StudentTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		StudentTest test = new StudentTest(manager);

		EntityTransaction tx = manager.getTransaction();
		System.out.println("1. create student records");
		tx.begin();
		try {
			test.createStudents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		System.out.println("2. read student records");
		test.readStudents();

		System.out.println("3. update a student record");
		tx.begin();
		try {
			test.updateStudent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();		

		System.out.println("4. delete a student record");
		tx.begin();
		try {
			test.deleteStudent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();		
		
		System.out.println("5. delete all student records");
		tx.begin();
		try {
			test.deleteStudents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();		
		
		System.out.println("=> done");
	}




	private void createStudents() {
		// insert a few records
		manager.persist(new Student(1,"Jack Jackson",3.28));
		manager.persist(new Student(2,"Tom Thomson",3.56));
		manager.persist(new Student(3,"Dave Davison",3.65));
		manager.persist(new Student(4,"Neil Nelson",3.56));
		manager.persist(new Student(5,"Mark Markson",3.78));		
	}


	private void readStudents() {
		List<Student> resultList = manager.createQuery("Select s From Student s order by s.id", Student.class).getResultList();
		System.out.println("num of students:" + resultList.size());
		for (Student next : resultList) {
			System.out.println("=> " + next);
		}
	}

	private void updateStudent() {
		// update a student record
		Student stu = manager.find(Student.class, 2);
        	if (stu != null) {
            System.out.println("from => "+stu);  
        	  stu.setName("Thomas Thomson");
            stu.setGpa(3.99);
            System.out.println("to => "+stu);
        	}
	}
	
	private void deleteStudent() {
		// delete ONE record
		Student stu = manager.find(Student.class, 2);
		System.out.println("remove=> "+stu);
		if (stu != null) {
		   manager.remove(stu);
		}

	}
	
	private void deleteStudents() {
		// delete all record
		int deletedCount = manager.createQuery("DELETE FROM Student").executeUpdate();
		System.out.println("total num of records removed => "+deletedCount);
	}
}
