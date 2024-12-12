import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuscarClientePageComponent } from './presentation/components/buscar-cliente-page/buscar-cliente-page.component';
import { ResumenClientePageComponent } from './presentation/components/resumen-cliente-page/resumen-cliente-page.component';
import { PrincipalComponent } from './presentation/pages/principal/principal.component';
import { NotificacionToastComponent } from './presentation/components/notificacion-toast/notificacion-toast.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { AuthInterceptorService } from 'src/service/auth-interceptor.service';
import { LoadingSpinnerComponent } from './presentation/components/loading-spinner/loading-spinner.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoadingInterceptorService } from 'src/service/loading-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    BuscarClientePageComponent,
    ResumenClientePageComponent,
    PrincipalComponent,
    NotificacionToastComponent,
    LoadingSpinnerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,           
    ReactiveFormsModule
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
