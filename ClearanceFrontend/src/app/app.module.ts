import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ClearanceRequestsComponent } from './pages/clearance-requests/clearance-requests.component';
import { PendingApprovalsComponent } from './pages/pending-approvals/pending-approvals.component';
import { ClearanceHistoryComponent } from './pages/clearance-history/clearance-history.component';
import { UserManagementComponent } from './pages/user-management/user-management.component';
import { RolesPermissionsComponent } from './pages/roles-permissions/roles-permissions.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { ReactiveFormsModule } from '@angular/forms';
import { ClearanceRequestFormComponent } from './pages/clearance-request-form/clearance-request-form.component';
import { StudentLayoutComponent } from './layouts/student-layout/student-layout.component';
import { ClearanceStatusComponent } from './pages/clearance-status/clearance-status.component';

@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    DashboardComponent,
    ProfileComponent,
    ClearanceRequestsComponent,
    PendingApprovalsComponent,
    ClearanceHistoryComponent,
    UserManagementComponent,
    RolesPermissionsComponent,
    LoginComponent,
    SignupComponent,
    ConfirmDialogComponent,
    ClearanceRequestFormComponent,
    StudentLayoutComponent,
    ClearanceStatusComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatTableModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
