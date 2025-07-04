import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClearanceHistoryComponent } from './clearance-history.component';

describe('ClearanceHistoryComponent', () => {
  let component: ClearanceHistoryComponent;
  let fixture: ComponentFixture<ClearanceHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClearanceHistoryComponent]
    });
    fixture = TestBed.createComponent(ClearanceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
