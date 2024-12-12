import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumenClientePageComponent } from './resumen-cliente-page.component';

describe('ResumenClientePageComponent', () => {
  let component: ResumenClientePageComponent;
  let fixture: ComponentFixture<ResumenClientePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResumenClientePageComponent]
    });
    fixture = TestBed.createComponent(ResumenClientePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
