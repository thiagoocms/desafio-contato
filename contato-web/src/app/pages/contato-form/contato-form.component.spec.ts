import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContatoFormComponent } from './contato-form.component';

describe('ContatoFormComponent', () => {
  let component: ContatoFormComponent;
  let fixture: ComponentFixture<ContatoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContatoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ContatoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
