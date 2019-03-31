package VVSS_SzuhaiBoanca.service;

import VVSS_SzuhaiBoanca.domain.Student;
import VVSS_SzuhaiBoanca.repository.NotaXMLRepo;
import VVSS_SzuhaiBoanca.repository.StudentXMLRepo;
import VVSS_SzuhaiBoanca.repository.TemaXMLRepo;
import VVSS_SzuhaiBoanca.validation.NotaValidator;
import VVSS_SzuhaiBoanca.validation.StudentValidator;
import VVSS_SzuhaiBoanca.validation.TemaValidator;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void addStudent() {
        Student a=new Student("1","Sara",931,"sara@gmail.com");
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent="fisiere/StudentiTest.xml";
        String filenameTema = "fisiere/TemeTest.xml";
        String filenameNota = "fisiere/NoteTest.xml";
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        int i1 = 0;
        Iterator it1=service.getAllStudenti().iterator();
        while(it1.hasNext()) {
            i1++;
            it1.next();
        }
        service.addStudent(a);
        int i = 0;
        Iterator it=service.getAllStudenti().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(1, i-i1);

    }
    @Test
    public void addStudent2() {
        Student a=new Student("1","Sara",931,"sara@gmail.com");
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent="fisiere/StudentiTest.xml";
        String filenameTema = "fisiere/TemeTest.xml";
        String filenameNota = "fisiere/NoteTest.xml";
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        service.addStudent(a);
        assertEquals(a.getNume(),service.findStudent("1").getNume());
    }
}