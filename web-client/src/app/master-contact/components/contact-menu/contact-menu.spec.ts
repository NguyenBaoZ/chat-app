import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactMenu } from './contact-menu';

describe('ContactMenu', () => {
  let component: ContactMenu;
  let fixture: ComponentFixture<ContactMenu>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContactMenu]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactMenu);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
