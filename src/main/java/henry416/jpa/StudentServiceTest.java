package henry416.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import henry416.domain.Student;
import henry416.jpa.StudentService;

public class StudentServiceTest {

    public static void main(String[] args) throws IOException {
        // init the EntityManager
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();
        StudentService service = new StudentService(em);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String action;
        int id;
        
        try {
            while (true) {
                System.out.println("\n\n\n[L]ist| [A]dd | [U]pdate | [R]emove | [Q]uit: \n\t\t\t");
                action = in.readLine();
                if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
                    break;
                }
    
                switch (action.toUpperCase().charAt(0)) {
                    case 'A':
                        System.out.println("Enter int value for student id: \n\t\t\t");
                        try {
                            id = new Integer(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
    
                        System.out.println("Enter value for student name: \n\t\t\t");
                        String name = in.readLine();
    
                        System.out.println("Enter double value for student's GPA: \n\t\t\t");
                        double gpa = 0;
                        try {
                            gpa = new Short(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
    
                        em.getTransaction().begin();
                        Student stu = service.createStudent(id, name, gpa);
                        em.getTransaction().commit();
                        
                        System.out.println("\n\nCreated " + stu);
                        break;
                        
                    case 'U':
                        System.out.println("Enter int value for student id: \n\t\t\t");
                        try {
                            id = new Integer(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
    
                        System.out.println("Enter double value for student's GPA: \n\t\t\t");
                        double gpa1 = 0;
                        try {
                            gpa1 = new Short(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
    
                        em.getTransaction().begin();
                        Student stu1 = service.updateStudentGpa(id, gpa1);
                        em.getTransaction().commit();
                        
                        System.out.println("\n\nUpdated " + stu1);

                        break;  
                          
                    case 'L':
                        Collection<Student> stus = service.findAllStudents();
                        System.out.println("\n\nFound students: " + stus);
                        break;
    
                    case 'R':
                        System.out.println("Enter int value for student id: \n\t\t\t");
                        try {
                            id = new Integer(in.readLine());
                        } catch (NumberFormatException e) {
                            break;
                        }
                        
                        em.getTransaction().begin();
                        service.removeStudent(id);
                        em.getTransaction().commit();
                        
                        System.out.println("\n\nRemoved Student " + id);
                        break;
                    default:
                        continue;
                }
            }
        } finally {        
            // close the EntityManager when done
            em.close();
            emf.close();
        }
    }
}
