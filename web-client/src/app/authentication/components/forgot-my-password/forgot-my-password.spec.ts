import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgotMyPassword } from './forgot-my-password';

describe('ForgotMyPassword', () => {
  let component: ForgotMyPassword;
  let fixture: ComponentFixture<ForgotMyPassword>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ForgotMyPassword]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForgotMyPassword);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
