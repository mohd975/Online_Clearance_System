import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClearanceStatusComponent } from './clearance-status.component';

describe('ClearanceStatusComponent', () => {
  let component: ClearanceStatusComponent;
  let fixture: ComponentFixture<ClearanceStatusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClearanceStatusComponent]
    });
    fixture = TestBed.createComponent(ClearanceStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
