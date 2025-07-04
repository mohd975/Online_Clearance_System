import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {}
  username = '';
  password = '';
  errorMessage = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private ngZone: NgZone
  ) {}

  onLogin() {
    if (this.username && this.password) {
      const loginData = {
        username: this.username,
        password: this.password,
      };
      this.authService.login(loginData).subscribe({
        next: (res) => {
          localStorage.setItem('token', res.token);
          const role = this.authService.getUserRole();
          console.log('Role is:', role);

          this.ngZone.run(() => {
            if (role === 'STUDENT' || role === 'USER') {
              this.router.navigateByUrl('/student');
            } else if (role === 'ADMIN') {
              this.router.navigateByUrl('/admin');
            } else if (role === 'HOD') {
              this.router.navigateByUrl('/hod');
            } else {
              this.errorMessage = 'Role is not recognized!';
            }
          });
        },
        error: (err) => {
          this.errorMessage = 'Login failed!';
        },
      });
    } else {
      this.errorMessage = 'Please enter both username and password';
    }
  }
}
