import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuscarClientePageComponent } from './presentation/components/buscar-cliente-page/buscar-cliente-page.component';
import { ResumenClientePageComponent } from './presentation/components/resumen-cliente-page/resumen-cliente-page.component';
import { PrincipalComponent } from './presentation/pages/principal/principal.component';

@NgModule({
  declarations: [
    AppComponent,
    BuscarClientePageComponent,
    ResumenClientePageComponent,
    PrincipalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
