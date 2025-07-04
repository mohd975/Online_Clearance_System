import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClearanceRequestFormComponent } from './clearance-request-form.component';

describe('ClearanceRequestFormComponent', () => {
  let component: ClearanceRequestFormComponent;
  let fixture: ComponentFixture<ClearanceRequestFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClearanceRequestFormComponent]
    });
    fixture = TestBed.createComponent(ClearanceRequestFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
