package VVSS_SzuhaiBoanca.app;


import VVSS_SzuhaiBoanca.repository.NotaXMLRepo;
import VVSS_SzuhaiBoanca.repository.StudentXMLRepo;
import VVSS_SzuhaiBoanca.repository.TemaXMLRepo;
import VVSS_SzuhaiBoanca.service.Service;
import VVSS_SzuhaiBoanca.validation.NotaValidator;
import VVSS_SzuhaiBoanca.validation.StudentValidator;
import VVSS_SzuhaiBoanca.validation.TemaValidator;
import VVSS_SzuhaiBoanca.view.UI;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


public class MainApplication {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

       //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        UI ui = new UI(service);
        ui.run();
    }

}
