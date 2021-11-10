import { TestBed } from '@angular/core/testing';

import { PasswordTokenService } from './password-token.service';

describe('PasswordTokenService', () => {
  let service: PasswordTokenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PasswordTokenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
