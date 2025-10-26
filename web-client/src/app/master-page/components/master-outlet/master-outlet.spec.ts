import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MasterOutlet } from './master-outlet';

describe('MasterOutlet', () => {
  let component: MasterOutlet;
  let fixture: ComponentFixture<MasterOutlet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MasterOutlet]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MasterOutlet);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
