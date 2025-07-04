import { TestBed } from '@angular/core/testing';

import { ClearanceRequestService } from './clearance-request.service';

describe('ClearanceRequestService', () => {
  let service: ClearanceRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClearanceRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
