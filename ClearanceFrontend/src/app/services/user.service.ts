import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = environment.apiUrl;
  constructor(private http: HttpClient) {}
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }

  getProfile(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
    return this.http.get(`${environment.apiUrl}/auth/profile`, { headers });
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/admin/users`, {
      headers: this.getAuthHeaders(),
    });
  }

  approveUser(id: number): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/admin/approve/${id}`,
      {},
      {
        headers: this.getAuthHeaders(),
      }
    );
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/admin/users/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  assignRole(id: number, role: string): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/admin/assign-role/${id}`,
      { role },
      {
        headers: this.getAuthHeaders(),
      }
    );
  }
}
