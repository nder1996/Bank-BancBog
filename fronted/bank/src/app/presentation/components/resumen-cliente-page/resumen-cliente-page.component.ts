import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteResponse } from 'src/model/response/ClienteResponse';
import { LocalStorageService } from 'src/service/local-storage.service';

@Component({
  selector: 'app-resumen-cliente-page',
  templateUrl: './resumen-cliente-page.component.html',
  styleUrls: ['./resumen-cliente-page.component.css']
})
export class ResumenClientePageComponent {

  constructor(private cdr: ChangeDetectorRef, private fb: FormBuilder,
    private router: Router,private localStorage:LocalStorageService) {}

    persona: ClienteResponse = new ClienteResponse();

    ngOnInit() {
      this.persona = new ClienteResponse(); 
      this.persona = this.localStorage.read("detallesUsuario");
    }

    ngAfterViewInit(): void {
      this.cdr.detectChanges();
    }

    resetDataPersona(){
      this.localStorage.remove("detallesUsuario");
      this.persona = new ClienteResponse();
      this.router.navigate(['/buscarCliente']);
    }

}
