import { TestBed } from '@angular/core/testing';

import { ShowSettingService } from './show-setting.service';

describe('ShowSettingService', () => {
  let service: ShowSettingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowSettingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
