package VVSS_SzuhaiBoanca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import VVSS_SzuhaiBoanca.domain.Student;
import VVSS_SzuhaiBoanca.domain.Tema;
import VVSS_SzuhaiBoanca.repository.NotaXMLRepo;
import VVSS_SzuhaiBoanca.repository.StudentXMLRepo;
import VVSS_SzuhaiBoanca.repository.TemaXMLRepo;
import VVSS_SzuhaiBoanca.service.Service;
import VVSS_SzuhaiBoanca.validation.NotaValidator;
import VVSS_SzuhaiBoanca.validation.StudentValidator;
import VVSS_SzuhaiBoanca.validation.TemaValidator;
import VVSS_SzuhaiBoanca.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private StudentValidator studentValidator;
    private TemaValidator temaValidator;
    private String filenameStudent="fisiere/StudentiTest.xml";
    private String filenameTema = "fisiere/TemeTest.xml";
    private String filenameNota = "fisiere/NoteTest.xml";
    StudentXMLRepo studentXMLRepository;
    TemaXMLRepo temaXMLRepository;
    NotaValidator notaValidator;
    NotaXMLRepo notaXMLRepository;
    Service service;

    @Before
    public void setup(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository = new TemaXMLRepo(filenameTema);
        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addStudent() {
        Student a=new Student("1","Sara",931,"sara@gmail.com");
        service.addStudent(a);
        int i = 0;
        Iterator it=service.getAllStudenti().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(1, i);
        assertEquals(a.getNume(),service.findStudent("1").getNume());
    }
    @Test
    public void addStudent2() {
        Student a=new Student("1","Sara",931,"sara@gmail.com");
        Student ret = service.addStudent(a);
        assertEquals(ret, a);
    }

    @Test(expected = ValidationException.class)
    public void EC_id_empty() {
        Student a = new Student("", "Iulia", 931, "sara@gmail.com");
        service.addStudent(a);
    }

    @Test(expected = NullPointerException.class)
    public void EC_id_null() {
        Student a = new Student(null, "Iulia", 931, "sara@gmail.com");
        service.addStudent(a);
    }

    @Test//(expected = NullPointerException.class)
    public void EC_id_not_unique() {

        Student a = new Student("1", "Iulia", 931, "sara@gmail.com");
        service.addStudent(a);
        int i = 0;
        Iterator it=service.getAllStudenti().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        Student b = new Student("1", "Sara", 931, "sara@gmail.com");
        service.addStudent(b);
        int i1 = 0;
        Iterator it1=service.getAllStudenti().iterator();
        while(it1.hasNext()) {
            i1++;
            it1.next();
        }
        assertEquals(i,i1);
    }

    @Test(expected = ValidationException.class)
    public void EC_name_empty() {
        Student a = new Student("1", "", 931, "sara@gmail.com");
        service.addStudent(a);
    }

    @Test(expected = ValidationException.class)
    public void EC_name_null() {
        Student a = new Student("1", null, 931, "sara@gmail.com");
        service.addStudent(a);
    }

    @Test(expected = ValidationException.class)
    public void EC_email_empty() {
        Student a = new Student("1", "Iulia", 931, "");
        service.addStudent(a);
    }

    @Test(expected = NullPointerException.class)
    public void EC_email_null() {
        Student a = new Student("1", "Iulia", 931, null);
        service.addStudent(a);
    }

    @Test(expected = ValidationException.class)
    public void EC_email_not_valid() {
        Student a = new Student("1", "Iulia", 931, "sara");
        service.addStudent(a);
    }

    @Test(expected = ValidationException.class)
    public void EC_group_too_low() {
        Student a = new Student("1", "Iulia", -1, "sara@yahoo.com");
        service.addStudent(a);
    }

    @Test(expected = ValidationException.class)
    public void EC_group_too_high() {
        Student a = new Student("1", "Iulia", 1000, "sara@yahoo.com");
        service.addStudent(a);
    }
///////////////////////////
    @Test
    public void BVA_group_lower_limit(){
        Student a = new Student("1", "Iulia", 0, "sara@yahoo.com");
        service.addStudent(a);
    }

    @Test
    public void BVA_group_lower_limit_plus(){
        Student a = new Student("1", "Iulia", 1, "sara@yahoo.com");
        service.addStudent(a);
    }

    @Test
    public void BVA_group_upper_limit(){
        Student a = new Student("1", "Iulia", 999, "sara@yahoo.com");
        service.addStudent(a);
    }
    @Test(expected = ValidationException.class)
    public void BVA_group_upper_limit_plus(){
        Student a = new Student("1", "Iulia", 1001, "sara@yahoo.com");
        service.addStudent(a);
    }

    @Test
    public void BVA_nume_lower_limit(){
        Student a = new Student("1", "I", 56, "sara@yahoo.com");
        service.addStudent(a);
    }


}
