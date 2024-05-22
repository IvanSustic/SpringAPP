import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopisUserComponent } from './popis-user.component';

describe('PopisUserComponent', () => {
  let component: PopisUserComponent;
  let fixture: ComponentFixture<PopisUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PopisUserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopisUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
