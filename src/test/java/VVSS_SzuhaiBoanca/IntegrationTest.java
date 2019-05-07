package VVSS_SzuhaiBoanca;

import VVSS_SzuhaiBoanca.curent.Curent;
import VVSS_SzuhaiBoanca.domain.Nota;
import VVSS_SzuhaiBoanca.domain.Student;
import VVSS_SzuhaiBoanca.domain.Tema;
import VVSS_SzuhaiBoanca.repository.NotaXMLRepo;
import VVSS_SzuhaiBoanca.repository.StudentXMLRepo;
import VVSS_SzuhaiBoanca.repository.TemaXMLRepo;
import VVSS_SzuhaiBoanca.service.Service;
import VVSS_SzuhaiBoanca.validation.NotaValidator;
import VVSS_SzuhaiBoanca.validation.StudentValidator;
import VVSS_SzuhaiBoanca.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
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
        assertEquals(3, i);
        assertEquals(a.getNume(),service.findStudent("1").getNume());
    }

    @Test
    public void  addAssignment(){
        Tema a=new Tema("1","Descriere",2,1);
        service.addTema(a);
        int i = 0;
        Iterator it=service.getAllTeme().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(1, i);
        //assertEquals(a.getNume(),service.findStudent("1").getNume());
    }

    @Test
    public void addGradeIntegration(){
        Tema a=new Tema("1","Descriere",2,1);
        service.addTema(a);
        int k = 0;
        Iterator it=service.getAllTeme().iterator();
        while(it.hasNext()) {
            k++;
            it.next();
        }
        assertEquals(1, k);
        Student sara=new Student("1","Sara",931,"sara@gmail.com");
        service.addStudent(sara);
        int j=0;
        Iterator it2=service.getAllStudenti().iterator();
        while(it2.hasNext()) {
            j++;
            it2.next();
        }
        assertEquals(3, j);
        Nota nota=new Nota("1","1","1",10,LocalDate.now());
        service.addNota(nota,"foarte bine");
        int i=0;
        Iterator it3=service.getAllNote().iterator();
        while(it3.hasNext()) {
            i++;
            it3.next();
        }
        assertEquals(2, i);
    }

    @Test
    public void addGrade(){
        Tema a=new Tema("1","Descriere",2,1);
        service.addTema(a);
        Student sara=new Student("1","Sara",931,"sara@gmail.com");
        service.addStudent(sara);
        Nota nota=new Nota("1","1","1",10,LocalDate.now());
        service.addNota(nota,"foarte bine");
        int i=0;
        Iterator it=service.getAllNote().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(2, i);
    }

    @Test
    public void addStudent2() {
        Student a=new Student("2","Sara2",931,"sara@gmail.com");
        service.addStudent(a);
        int i = 0;
        Iterator it=service.getAllStudenti().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(3, i);
        //assertEquals(a.getNume(),service.findStudent("1").getNume());
    }

    @Test
    public void  addAssignment2(){
        Student a=new Student("3","Sara3",931,"sara@gmail.com");
        service.addStudent(a);
        int i = 0;
        Iterator it=service.getAllStudenti().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(3, i);
        Tema a1=new Tema("1","Descriere",2,1);
        service.addTema(a1);
        int i1 = 0;
        Iterator it1=service.getAllTeme().iterator();
        while(it1.hasNext()) {
            i1++;
            it1.next();
        }
        assertEquals(1, i1);
        //assertEquals(a.getNume(),service.findStudent("1").getNume());
    }

    @Test
    public void addGrade2(){
        Tema a=new Tema("1","Descriere",2,1);
        service.addTema(a);
        int i1 = 0;
        Iterator it1=service.getAllTeme().iterator();
        while(it1.hasNext()) {
            i1++;
            it1.next();
        }
        assertEquals(1, i1);
        Student sara=new Student("1","Sara",931,"sara@gmail.com");
        service.addStudent(sara);
        int i = 0;
        Iterator it=service.getAllStudenti().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(3, i);
        Nota nota=new Nota("1","1","1",10,LocalDate.now());
        service.addNota(nota,"foarte bine");
        int i2=0;
        Iterator it2=service.getAllNote().iterator();
        while(it2.hasNext()) {
            i2++;
            it2.next();
        }
        assertEquals(2, i2);
    }
}
