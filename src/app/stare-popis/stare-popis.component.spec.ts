import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StarePopisComponent } from './stare-popis.component';

describe('StarePopisComponent', () => {
  let component: StarePopisComponent;
  let fixture: ComponentFixture<StarePopisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StarePopisComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StarePopisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
