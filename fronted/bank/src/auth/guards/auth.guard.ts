import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { ClienteResponse } from "src/model/response/ClienteResponse";
import { LocalStorageService } from "src/service/local-storage.service";

@Injectable({ providedIn: 'root' })
export class AuthGuard {
 constructor(private router: Router, private dataPersona: LocalStorageService) {}

 canActivate(): boolean {
  const detalle:ClienteResponse = this.dataPersona.read("detallesUsuario");
   if (detalle && detalle.primerNombre) return true;
   this.router.navigate(['/buscarCliente']);
   return false;
 }
}