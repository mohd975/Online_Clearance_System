import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface ClearanceRequest {
  semester: string;
  year: number;
  department: {
    id: number;
  };
}


@Injectable({
  providedIn: 'root',
})
export class ClearanceRequestService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  submitClearanceRequest(request: ClearanceRequest): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
    return this.http.post(`${this.apiUrl}/clearanceForms`, request, {
      headers,
    });
  }
}
