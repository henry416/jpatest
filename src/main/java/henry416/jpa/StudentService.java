package henry416.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import henry416.domain.Student;

public class StudentService {
    protected EntityManager em;

    public StudentService(EntityManager em) {
        this.em = em;
    }

    public Student createStudent(int id, String name, double gpa) {
        Student stu = new Student(id, name, gpa);
        em.persist(stu);
        return stu;
    }

    public void removeStudent(int id) {
        Student stu = findStudent(id);
        if (stu != null) {
            em.remove(stu);
        }
    }

    public Student updateStudentGpa(int id, double gpa) {
        Student stu = em.find(Student.class, id);
        if (stu != null) {
            stu.setGpa(gpa);
        }
        return stu;
    }

    public Student findStudent(int id) {
        return em.find(Student.class, id);
    }

    public Collection<Student> findAllStudents() {
        Query query = em.createQuery("SELECT e FROM Student e");
        return (Collection<Student>) query.getResultList();
    }
}
