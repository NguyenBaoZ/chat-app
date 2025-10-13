import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticationOutlet } from './authentication-outlet';

describe('AuthenticationOutlet', () => {
  let component: AuthenticationOutlet;
  let fixture: ComponentFixture<AuthenticationOutlet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AuthenticationOutlet]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthenticationOutlet);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
