import { Component } from '@angular/core';
import { DepartmentService } from './department.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  departmentName: string = "";
  location: string = "";
  email: string = "";
  status: string = "";
  msg: string = "";

  departments: any = [];
  showAllDepartments: boolean = false;

  departmentDetails: any = {};

  constructor(private departmentService: DepartmentService) {}

  async handleRegClick() {
    const department = {
      departmentName: this.departmentName,
      location: this.location,
      email: this.email,
      status: this.status
    };

    try {
      const response = await this.departmentService.createDepartment(department).toPromise();
      this.msg = response;
    } catch (error) {
      console.error('Error Creating Department:', error);
      this.msg = 'Department Registred suessfully';
    }
  }


  getDepartments() {
    this.departmentService.getAllDepartments().subscribe(
      (data: any) => {
        this.departments = data;
      },
      (error: any) => {
        console.error('Error Getting Departments:', error);
      }
    );
  }

  showDepartments() {
    this.getDepartments();
    this.showAllDepartments = true;
  }

  async getDepartmentById(departmentId: number) {
    try {
      const response = await this.departmentService.getDepartmentById(departmentId).toPromise();
      this.departmentDetails = response;
      console.log('Department Details:', this.departmentDetails);
    } catch (error) {
      console.error('Error Fetching Department:', error);
    }
  }

}

