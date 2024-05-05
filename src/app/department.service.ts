import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
 

@Injectable({ providedIn: 'root' })
export class DepartmentService {

  constructor(private http: HttpClient) { }

  createDepartment(department: any): Observable<any> {
    return this.http.post<any>('http://localhost:8081/department/create', department);
  }


  getAllDepartments(): Observable<any> {
    return this.http.get<any>('http://localhost:8081/department/getAll');
  }

  getDepartmentById(departmentId: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8081/department/get/${departmentId}`);
  }
}

