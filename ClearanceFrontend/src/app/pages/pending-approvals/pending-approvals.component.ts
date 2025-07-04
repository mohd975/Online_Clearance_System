import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from 'src/app/components/confirm-dialog/confirm-dialog.component';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-pending-approvals',
  templateUrl: './pending-approvals.component.html',
  styleUrls: ['./pending-approvals.component.scss'],
})
export class PendingApprovalsComponent implements OnInit {
  pendingApprovals: any[] = [];
  loading = false;
  error = '';

  constructor(
    private adminService: AdminService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}
  ngOnInit(): void {
    this.loadPendingApprovals();
  }

  loadPendingApprovals() {
    this.loading = true;
    this.adminService.getPendingUsers().subscribe({
      next: (users) => {
        this.pendingApprovals = users;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load pending users';
        this.loading = false;
      },
    });
  }

  approveUser(userId: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { message: 'Are you sure you want to approve this user?' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.adminService.approveUser(userId).subscribe({
          next: () => {
            this.snackBar.open('User approved successfully', 'Close', {
              duration: 3000,
            });
            this.loadPendingApprovals();
          },
          error: () => {
            this.snackBar.open('Failed to approve user', 'Close', {
              duration: 3000,
            });
          },
        });
      }
    });
  }

  rejectUser(userId: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { message: 'Are you sure you want to reject this user?' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.adminService.rejectUser(userId).subscribe({
          next: () => {
            this.snackBar.open('User rejected successfully', 'Close', {
              duration: 3000,
            });
            this.loadPendingApprovals();
          },
          error: () => {
            this.snackBar.open('Failed to reject user', 'Close', {
              duration: 3000,
            });
          },
        });
      }
    });
  }
}
