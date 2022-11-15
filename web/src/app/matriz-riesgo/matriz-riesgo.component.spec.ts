import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatrizRiesgoComponent } from './matriz-riesgo.component';

describe('MatrizRiesgoComponent', () => {
  let component: MatrizRiesgoComponent;
  let fixture: ComponentFixture<MatrizRiesgoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatrizRiesgoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatrizRiesgoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
