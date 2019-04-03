package VVSS_SzuhaiBoanca;

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

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class WTBAssignmentTest {
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
    public void setup() {
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository = new TemaXMLRepo(filenameTema);
        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void  addAssignment(){
        Tema a=new Tema("1","Descriere",10,8);
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
    public void addAssignment2(){
        Tema a=new Tema("1","Descriere",10,8);
        service.addTema(a);
        service.addTema(a);
        int i = 0;
        Iterator it=service.getAllTeme().iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        assertEquals(1, i);
    }


}
