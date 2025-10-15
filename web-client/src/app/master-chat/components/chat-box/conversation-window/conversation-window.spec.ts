import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversationWindow } from './conversation-window';

describe('ConversationWindow', () => {
  let component: ConversationWindow;
  let fixture: ComponentFixture<ConversationWindow>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConversationWindow]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConversationWindow);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
