import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarClientePageComponent } from './buscar-cliente-page.component';

describe('BuscarClientePageComponent', () => {
  let component: BuscarClientePageComponent;
  let fixture: ComponentFixture<BuscarClientePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuscarClientePageComponent]
    });
    fixture = TestBed.createComponent(BuscarClientePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
