import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoxHeader } from './box-header';

describe('BoxHeader', () => {
  let component: BoxHeader;
  let fixture: ComponentFixture<BoxHeader>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BoxHeader]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoxHeader);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
