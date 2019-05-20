package VVSS_SzuhaiBoanca.controller;

import VVSS_SzuhaiBoanca.domain.Student;
import VVSS_SzuhaiBoanca.repository.NotaXMLRepo;
import VVSS_SzuhaiBoanca.repository.StudentXMLRepo;
import VVSS_SzuhaiBoanca.repository.TemaXMLRepo;
import VVSS_SzuhaiBoanca.service.Service;
import VVSS_SzuhaiBoanca.validation.NotaValidator;
import VVSS_SzuhaiBoanca.validation.StudentValidator;
import VVSS_SzuhaiBoanca.validation.TemaValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class AddStudentController {
    private StudentValidator studentValidator ;
    private TemaValidator temaValidator ;
    private String filenameStudent ;
    private String filenameTema ;
    private String filenameNota;
    private StudentXMLRepo studentXMLRepository ;
    private TemaXMLRepo temaXMLRepository ;
    private NotaValidator notaValidator ;
    private NotaXMLRepo notaXMLRepository;
    private Service service;

    public AddStudentController(){
        this.studentValidator = new StudentValidator();
        this.temaValidator = new TemaValidator();
        this.filenameStudent = "fisiere/Studenti.xml";
        this.filenameTema = "fisiere/Teme.xml";
        this.filenameNota = "fisiere/Note.xml";
        this.studentXMLRepository = new StudentXMLRepo(filenameStudent);
        this.temaXMLRepository = new TemaXMLRepo(filenameTema);
        this.notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        this.notaXMLRepository = new NotaXMLRepo(filenameNota);
        this.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    }

    @RequestMapping(method = RequestMethod.POST, value="/addStudent")
        public ResponseEntity addStudent(@RequestBody Student s) throws Exception {
            Student rez=this.service.addStudent(s);
            System.out.println(rez);
            if( rez== null)
                return new ResponseEntity("good",HttpStatus.OK);
            else
                return new ResponseEntity("bad",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public Iterable<Student> getStudents() throws Exception {
        Iterable<Student> students=service.getAllStudenti();
        return students;
    }


}
