import { Component } from '@angular/core';
import {Student, StudentService} from "./student.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MyCatalog';
  student: Student;
  error: boolean;


  constructor(private studentService:StudentService, private router: Router) {
    this.student = {
      id : '',
      nume:'',
      grupa:0,
      email:''
    };
    this.error = false;

  }

  displayError(){
    return this.error;
  }


  onSubmit(){
    this.studentService.loginUser(this.student)
      .subscribe(
        data => {
          console.log(data);
          this.error = false;
        },
        err => {
          this.error = true;

        }
      );
  }

}
