import { TestBed } from '@angular/core/testing';

import { AuthGuardGuardService } from './auth-guard-guard.service';

describe('AuthGuardGuardService', () => {
  let service: AuthGuardGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuardGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
