import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrincipalComponent } from './presentation/pages/principal/principal.component';
import { BuscarClientePageComponent } from './presentation/components/buscar-cliente-page/buscar-cliente-page.component';
import { ResumenClientePageComponent } from './presentation/components/resumen-cliente-page/resumen-cliente-page.component';

const routes: Routes = [
  { path: '', redirectTo: 'principal', pathMatch: 'full' },
  { path: 'principal', component: PrincipalComponent },
  { path: 'buscarCliente1', component: BuscarClientePageComponent },
  { path: 'ResumenCliente1', component: ResumenClientePageComponent },
  { 
    path: 'principal', 
    component: PrincipalComponent,
    children: [
      { path: 'buscarCliente', component: BuscarClientePageComponent },
      { path: 'ResumenCliente', component: ResumenClientePageComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
