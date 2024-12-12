import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../enviroments/enviroment';


@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

    private readonly API_KEY_HEADER = environment.apiKeyHeader;
    private readonly API_KEY_KEY = environment.apiKeyValue;


  
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      // Clone request and add headers
      const authReq = req.clone({
        headers: req.headers
          .set(this.API_KEY_HEADER, this.API_KEY_KEY)
          .set('Content-Type', 'application/json')
      });
  
      // Log outgoing request details
      this.logRequest(authReq);
  
      // Handle the request and monitor for success/failure
      return next.handle(authReq).pipe(
        tap({
          next: (event: HttpEvent<any>) => {
            // Uncomment if you want success logging
             console.log('✅ Respuesta exitosa para:', req.url);
          },
          error: (error: HttpErrorResponse) => {
            this.logError(req, error);
          }
        })
      );
    }
  
    private logRequest(request: HttpRequest<any>): void {
      console.log("📡 Request:", {
        url: request.url,
        method: request.method,
        headers: request.headers.keys(),
        timestamp: new Date().toISOString()
      });
    }
  
    private logError(request: HttpRequest<any>, error: HttpErrorResponse): void {
      console.error('❌ Error en la petición:', {
        url: request.url,
        status: error.status,
        message: error.message,
        timestamp: new Date().toISOString()
      });
    }
  
  
}