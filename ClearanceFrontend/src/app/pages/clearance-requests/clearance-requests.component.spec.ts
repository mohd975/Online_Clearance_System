import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClearanceRequestsComponent } from './clearance-requests.component';

describe('ClearanceRequestsComponent', () => {
  let component: ClearanceRequestsComponent;
  let fixture: ComponentFixture<ClearanceRequestsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClearanceRequestsComponent]
    });
    fixture = TestBed.createComponent(ClearanceRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
