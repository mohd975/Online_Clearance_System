import { TestBed } from '@angular/core/testing';

import { ClearanceService } from './clearance.service';

describe('ClearanceService', () => {
  let service: ClearanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClearanceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
