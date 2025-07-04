import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private apiUrl = environment.apiUrl;
  constructor(private http: HttpClient) {}

  private getAuthHeaders() {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }
  getPendingUsers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/admin/pending-users`, {
      headers: this.getAuthHeaders(),
    });
  }

  approveUser(userId: number): Observable<any> {
    return this.http.put(
      this.apiUrl + `admin/approve/${userId}`,
      {},
      {
        headers: this.getAuthHeaders(),
      }
    );
  }

  rejectUser(userId: number): Observable<any> {
    return this.http.put(
      this.apiUrl + `admin/reject/${userId}`,
      {},
      {
        headers: this.getAuthHeaders(),
      }
    );
  }
}
