import { Component, OnInit } from '@angular/core';
import { ClearanceService } from '../../services/clearance.service';

@Component({
  selector: 'app-clearance-status',
  templateUrl: './clearance-status.component.html',
  styleUrls: ['./clearance-status.component.scss'],
})
export class ClearanceStatusComponent implements OnInit {
  forms: any[] = [];
  message = '';

  constructor(private clearanceService: ClearanceService) {}

  ngOnInit(): void {
    this.loadClearanceStatus();
  }

  loadClearanceStatus() {
    this.clearanceService.getMyClearanceForms().subscribe({
      next: (data: any) => {
        this.forms = data;
      },
      error: (err: any) => {
        this.message = 'Failed to load clearance status.';
      },
    });
  }
}
