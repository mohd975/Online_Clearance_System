import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ClearanceService } from '../../services/clearance.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-clearance-requests',
  templateUrl: './clearance-requests.component.html',
  styleUrls: ['./clearance-requests.component.scss'],
})
export class ClearanceRequestsComponent implements OnInit {
  constructor(
    private clearanceService: ClearanceService,
    private http: HttpClient
  ) {}

  clearanceForms: any[] = [];
  errorMessage = '';
  successMessage = '';
  loading = false;

  ngOnInit(): void {
    this.fetchClearanceForms();
    console.log("Loaded forms:", this.clearanceForms);


  }
  fetchClearanceForms(): void {
    this.loading = true;
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    this.http
      .get<any[]>(`${environment.apiUrl}/my-clearance-forms`, { headers })
      .subscribe({
        next: (data) => {
          this.clearanceForms = data;
          this.loading = false;
        },
        error: (error) => {
          console.error('Failed to load clearance forms', error);
          this.errorMessage = 'Failed to load clearance requests';
          this.loading = false;
        },
      });
  }

  approveClearance(id: number) {
    this.clearanceService.approveClearance(id).subscribe({
      next: () => {
        this.successMessage = 'Clearance approved';
        this.fetchClearanceForms();
      },
      error: () => {
        this.errorMessage = 'Failed to approve clearance';
      },
    });
  }

  rejectClearance(id: number) {
    this.clearanceService.rejectClearance(id).subscribe({
      next: () => {
        this.successMessage = 'Clearance rejected';
        this.fetchClearanceForms();
      },
      error: () => {
        this.errorMessage = 'Failed to reject clearance';
      },
    });
  }
}
