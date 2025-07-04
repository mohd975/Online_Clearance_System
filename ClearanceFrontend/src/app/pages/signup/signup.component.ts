import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  username = '';
  email = '';
  password = '';

  constructor(private router: Router, private authService: AuthService) {}
  ngOnInit(): void {}

  onSignup() {
    if (this.username && this.email && this.password) {
      const signupData = {
        username: this.username,
        email: this.email,
        password: this.password,
      };

      this.authService.signup(signupData).subscribe({
        next: (response) => {
          console.log('signup Successful', response);
          alert(response.token);
          this.router.navigateByUrl('/login');
        },
        error: (error) => {
          console.log('Signup failed', error);
          alert('Signup failed, please try again');
        },
      });
    } else {
      alert('Please fill in all require fields');
    }
  }
}
