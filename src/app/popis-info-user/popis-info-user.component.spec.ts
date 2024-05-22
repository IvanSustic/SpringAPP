import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopisInfoUserComponent } from './popis-info-user.component';

describe('PopisInfoUserComponent', () => {
  let component: PopisInfoUserComponent;
  let fixture: ComponentFixture<PopisInfoUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PopisInfoUserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopisInfoUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
