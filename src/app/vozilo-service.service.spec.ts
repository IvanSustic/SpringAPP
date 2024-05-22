import { TestBed } from '@angular/core/testing';

import { VoziloServiceService } from './vozilo-service.service';

describe('VoziloServiceService', () => {
  let service: VoziloServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VoziloServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
