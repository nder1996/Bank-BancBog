import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroment';
import { ApiResponse } from 'src/model/ApiResponse';
import { TipoDocumentoResponse } from 'src/model/response/TipoDocumentoResponse';

@Injectable({
  providedIn: 'root'
})
export class ReferenceDataService {

  constructor(private http: HttpClient) { }

  referenceData = environment.apiRestURL + 'api/v1/reference'


  getAllTipoDocumento(): Observable<ApiResponse<TipoDocumentoResponse[]>> {
    return this.http.get<ApiResponse<TipoDocumentoResponse[]>>(`${this.referenceData}/getAllTipoDocumento`);
  }
  
  
}
