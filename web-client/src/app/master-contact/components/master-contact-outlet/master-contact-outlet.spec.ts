import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MasterContactOutlet } from './master-contact-outlet';

describe('MasterContactOutlet', () => {
  let component: MasterContactOutlet;
  let fixture: ComponentFixture<MasterContactOutlet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MasterContactOutlet]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MasterContactOutlet);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
