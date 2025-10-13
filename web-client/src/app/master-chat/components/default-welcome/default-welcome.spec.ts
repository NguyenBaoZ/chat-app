import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultWelcome } from './default-welcome';

describe('DefaultWelcome', () => {
  let component: DefaultWelcome;
  let fixture: ComponentFixture<DefaultWelcome>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DefaultWelcome]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefaultWelcome);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
