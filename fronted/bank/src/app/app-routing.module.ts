import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrincipalComponent } from './presentation/pages/principal/principal.component';
import { BuscarClientePageComponent } from './presentation/components/buscar-cliente-page/buscar-cliente-page.component';
import { ResumenClientePageComponent } from './presentation/components/resumen-cliente-page/resumen-cliente-page.component';
import { AuthGuard } from 'src/auth/guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'buscarCliente', pathMatch: 'full' },
  {
    path: 'buscarCliente',
    component: BuscarClientePageComponent
  },
  {
    path: 'ResumenCliente',
    component: ResumenClientePageComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
