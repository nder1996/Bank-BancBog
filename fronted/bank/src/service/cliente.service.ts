import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    return this.http.post<ApiResponse<ClienteResponse>>(`${this.clienteUrl}/consulta`, request);
  }


}
