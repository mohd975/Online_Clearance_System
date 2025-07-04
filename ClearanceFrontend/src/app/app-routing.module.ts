import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { UserManagementComponent } from './pages/user-management/user-management.component';
import { ClearanceRequestsComponent } from './pages/clearance-requests/clearance-requests.component';
import { PendingApprovalsComponent } from './pages/pending-approvals/pending-approvals.component';
import { ClearanceHistoryComponent } from './pages/clearance-history/clearance-history.component';
import { RolesPermissionsComponent } from './pages/roles-permissions/roles-permissions.component';
import { AuthGuard } from './guards/auth.guard';
import { ProfileComponent } from './pages/profile/profile.component';
import { StudentLayoutComponent } from './layouts/student-layout/student-layout.component';
import { ClearanceRequestFormComponent } from './pages/clearance-request-form/clearance-request-form.component';
import { ClearanceStatusComponent } from './pages/clearance-status/clearance-status.component';

const routes: Routes = [
  {
    path: 'student',
    component: StudentLayoutComponent,
    children: [
      {
        path: 'clearance-request-form',
        component: ClearanceRequestFormComponent,
      },
      {
        path: 'profile',
        component: ProfileComponent,
      },

      {
        path: 'clearance-status',
        component: ClearanceStatusComponent,
      },
      {
        path: '',
        redirectTo: '/student/clearance-request-form',
        pathMatch: 'full',
      },
      { path: '**', redirectTo: '/student/clearance-request-form' },
    ],
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: DashboardComponent,
      },
      {
        path: 'user-profile',
        component: ProfileComponent,
      },
      {
        path: 'clearance-requests',
        component: ClearanceRequestsComponent,
      },
      {
        path: 'pending-approvals',
        component: PendingApprovalsComponent,
      },
      {
        path: 'clearance-history',
        component: ClearanceHistoryComponent,
      },
      {
        path: 'user-management',
        component: UserManagementComponent,
      },
      {
        path: 'roles-permissions',
        component: RolesPermissionsComponent,
      },
    ],
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
