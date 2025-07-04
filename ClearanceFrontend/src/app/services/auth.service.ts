import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { jwtDecode } from 'jwt-decode';

export interface DecodedToken {
  role: string;
  sub: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  constructor(private route: Router, private http: HttpClient) {}

  login(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/auth/login`, data, { headers });
  }

  signup(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/auth/signup`, data, { headers });
  }

  logout() {
    localStorage.removeItem('token');
    this.route.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
  getUserRole(): string | null {
    const token = localStorage.getItem('token');
    if (!token) return null;

    const decoded = jwtDecode<DecodedToken>(token);
    console.log('Decoded role:', decoded.role);
    return decoded.role;
  }
}
