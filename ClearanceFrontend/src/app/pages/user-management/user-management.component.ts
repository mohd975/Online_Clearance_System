import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss'],
})
export class UserManagementComponent implements OnInit {
  constructor(private userService: UserService) {}
  users: any[] = [];
  loading = false;
  errorMessage = '';
  successMessage = '';
  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.loading = true;
    this.userService.getAllUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
      },
      error: (error: HttpErrorResponse) => {
        this.errorMessage = 'Failed to load users';
        this.loading = false;
      },
    });
  }

  assignRole(id: number, role: string): void {
    if (!role) {
      alert('Please enter a role');
      return;
    }

    this.userService.assignRole(id, role).subscribe({
      next: (response) => {
        this.successMessage = response.message || 'Role assigned successfully';
        this.errorMessage = '';
        this.fetchUsers();
      },
      error: (error) => {
        console.error(error);
        this.errorMessage = error?.error?.message || 'Failed to assign role';
        this.successMessage = '';
      },
    });
  }

  approveUser(id: number): void {
    this.userService.approveUser(id).subscribe({
      next: (response) => {
        this.successMessage = response.message || 'User approved successfully';
        this.errorMessage = '';
        this.fetchUsers();
      },
      error: (error: HttpErrorResponse) => {
        this.errorMessage = 'Failed to approve user';
        this.successMessage = '';
        console.error(error);
      },
    });
  }

  deleteUser(id: number): void {
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteUser(id).subscribe({
        next: (response) => {
          this.successMessage = response.message;
          this.errorMessage = '';
          this.fetchUsers();
        },
        error: (error) => {
          console.error(error);
          this.errorMessage = error?.error?.message || 'Failed to delete user';
          this.successMessage = '';
        },
      });
    }
  }
}
