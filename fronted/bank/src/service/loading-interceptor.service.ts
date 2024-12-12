import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, finalize } from 'rxjs';
import { LoadingService } from './loading.service';

@Injectable({
  providedIn: 'root'
})
export class LoadingInterceptorService implements HttpInterceptor{

  private sessionLoaded = false; // Bandera para indicar si se ha cargado la sesión al menos una vez
  private buttonClicked = new BehaviorSubject(false);

  constructor(private loadingService: LoadingService) {
    this.buttonClicked.subscribe((clicked) => {
      if (clicked) {
        this.loadingService.startLoading();
      }
    });
  }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Aquí debería cambiar el estado del botón a clicked cuando la solicitud se realiza
    this.buttonClicked.next(true);

    return next.handle(request).pipe(
      finalize(() => {
        this.loadingService.stopLoading();
        this.buttonClicked.next(false); // Reinicia el estado después de completar la solicitud
      })
    );
  }
}
