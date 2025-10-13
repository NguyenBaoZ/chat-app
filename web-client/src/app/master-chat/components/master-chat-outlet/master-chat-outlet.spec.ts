import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MasterChatOutlet } from './master-chat-outlet';

describe('MasterChatOutlet', () => {
  let component: MasterChatOutlet;
  let fixture: ComponentFixture<MasterChatOutlet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MasterChatOutlet]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MasterChatOutlet);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
