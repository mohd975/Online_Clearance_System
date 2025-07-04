import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClearanceRequestService } from '../../requests/clearance-request.service';
import { HttpClient } from '@angular/common/http';

interface Department {
  id: number;
  name: string;
}

@Component({
  selector: 'app-clearance-request-form',
  templateUrl: './clearance-request-form.component.html',
  styleUrls: ['./clearance-request-form.component.scss'],
})
export class ClearanceRequestFormComponent implements OnInit {
  clearanceForm: FormGroup;
  message = '';
  departments: any[] = [];

  constructor(
    private fb: FormBuilder,
    private clearanceService: ClearanceRequestService,
    private http: HttpClient
  ) {
    this.clearanceForm = this.fb.group({
      semester: ['', Validators.required],
      year: ['', [Validators.required, Validators.min(2000)]],
      department: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadDepartments();
    this.http.get<Department[]>('http://localhost:8081/departments').subscribe({
      next: (data) => (this.departments = data),
      error: (err) => console.error('Failed to load departments:', err),
    });
  }

  loadDepartments(): void {
    this.http.get<any[]>('http://localhost:8081/departments').subscribe({
      next: (data) => {
        this.departments = data;
      },
      error: (err) => {
        console.error('Failed to load departments:', err);
      },
    });
  }

  submitForm(): void {
    if (this.clearanceForm.valid) {
      const formValues = this.clearanceForm.value;

      const requestPayload = {
        semester: formValues.semester,
        year: formValues.year,
        department: {
          id: formValues.department,
        },
      };

      this.clearanceService.submitClearanceRequest(requestPayload).subscribe({
        next: () => {
          this.message = 'Request submitted successfully!';
          this.clearanceForm.reset();
        },
        error: (err: any) => {
          console.error(err);
          this.message = 'Submission failed. Please try again.';
        },
      });
    } else {
      this.message = 'Please fill the form correctly.';
    }
  }
}
