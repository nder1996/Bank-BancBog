import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/enviroments/enviroment";
import { ApiResponse } from "src/model/ApiResponse";
import { ClienteRequest } from "src/model/request/clienteRequest";




@Injectable({
  providedIn: 'root'
})
export class ClienteService {


  clienteUrl = environment.apiRestURL + '/api/v1/consulta'

  constructor(private http: HttpClient) { }


  byCliente(request: ClienteRequest): Observable<ApiResponse<ClienteRequest>> {
    const options = {
      body: request,
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.request<ApiResponse<ClienteRequest>>('GET', this.clienteUrl, options);
  }




}