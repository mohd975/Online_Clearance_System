import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClearanceService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }

  getAllClearanceForm(): Observable<any> {
    return this.http.get(`${this.apiUrl}clearanceForms`, {
      headers: this.getAuthHeaders(),
    });
  }

  getClearanceFormById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}clearanceForms/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  createClearanceForm(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}clearanceForms`, data, {
      headers: this.getAuthHeaders(),
    });
  }

  updateClearanceForm(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}clearanceForms/${id}`, data, {
      headers: this.getAuthHeaders(),
    });
  }

  deleteClearanceForm(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}clearanceForms/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  getMyClearanceForms(): Observable<any> {
    return this.http.get(`${this.apiUrl}/my-clearance-forms`, {
      headers: this.getAuthHeaders(),
    });
  }

  approveClearance(id: number): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/admin/clearanceForms/${id}/approve`,
      {},
      {
        headers: this.getAuthHeaders(),
      }
    );
  }

  rejectClearance(id: number): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/admin/clearanceForms/${id}/reject`,
      {},
      {
        headers: this.getAuthHeaders(),
      }
    );
  }
}
