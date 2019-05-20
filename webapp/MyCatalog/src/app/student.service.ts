import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';



export interface Student {
  id:string;
  nume:string;
  grupa:number;
  email:string;

}
@Injectable()
export class StudentService {
  baseURL = 'http://localhost:8080/students/addStudent';

  constructor(private http: HttpClient,
              private router: Router) {
  }


  loginUser(student:Student){
    //console.log(username, password);

    return this.http.post<Student>(this.baseURL,student);
  }
}
