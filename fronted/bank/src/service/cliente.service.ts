import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroment';
import { ApiResponse } from 'src/model/ApiResponse';
import { ClienteRequest } from 'src/model/request/clienteRequest';
import { ClienteResponse } from 'src/model/response/ClienteResponse';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  clienteUrl = environment.apiRestURL + 'api/v1/cliente'

  constructor(private http: HttpClient) { }

  byCliente(request: ClienteRequest): Observable<ApiResponse<ClienteResponse>> {
    const params = new HttpParams()
      .set('codigo', (request.codigo ?? "N" ))
      .set('numeroDocumento', (request.numeroDocumento ?? 0 ));
      return this.http.get<ApiResponse<ClienteResponse>>(`${this.clienteUrl}/consulta`, { params });
  }


}
