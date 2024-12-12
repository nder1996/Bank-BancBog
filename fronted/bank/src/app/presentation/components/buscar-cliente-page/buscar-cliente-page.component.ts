import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, EMPTY, firstValueFrom } from 'rxjs';
import { ApiResponse } from 'src/model/ApiResponse';
import { ClienteRequest } from 'src/model/request/clienteRequest';
import { ClienteResponse } from 'src/model/response/ClienteResponse';
import { TipoDocumentoResponse } from 'src/model/response/TipoDocumentoResponse';
import { ClienteService } from 'src/service/cliente.service';
import { LocalStorageService } from 'src/service/local-storage.service';
import { NotificationToastService } from 'src/service/notification-toast.service';
import { ReferenceDataService } from 'src/service/reference-data.service';

@Component({
  selector: 'app-buscar-cliente-page',
  templateUrl: './buscar-cliente-page.component.html',
  styleUrls: ['./buscar-cliente-page.component.css']
})
export class BuscarClientePageComponent {

  byClienteForm!: FormGroup;

  constructor(private cdr: ChangeDetectorRef, private fb: FormBuilder,
    private router: Router, private clienteService: ClienteService,
    private toastService: NotificationToastService, private localStorage: LocalStorageService,
    private referenceDataService: ReferenceDataService) {
    this.byClienteForm = this.fb.group({
      documentType: ['', [Validators.required]],
      documentNumber: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(11),
        Validators.pattern('^[0-9]*$')
      ]]
    });
  }


  detalleCliente: ClienteResponse = new ClienteResponse();
  ListTipoDocumento: TipoDocumentoResponse[] = [];

  async ngOnInit() {
    await this.getAllTipoDocumento();
    this.toastService.showToast('¡Bienvenido al servicio de consulta de personas de Banco Bogotá!', 'info');
  }

  formatDocumentNumber(event: any): void {
    // Eliminar cualquier carácter que no sea un número
    let value = event.target.value.replace(/\D/g, '');
    // Limitar el número a 11 dígitos
    if (value.length > 11) {
      value = value.substring(0, 11);
    }
    // Actualizar el valor del formulario y del input
    this.byClienteForm.get('documentNumber')?.setValue(value, { emitEvent: false });
    event.target.value = value;
  }


  ngAfterViewInit(): void {
    this.cdr.detectChanges();

  }


  reloadPage() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]).then(() => {
        this.cdr.detectChanges(); // Asegura la actualización de la vista
      });
    });
  }

  async getByCliente(): Promise<void> {
    try {
      if (!this.byClienteForm.valid) {
        return;
      }
      
      const cliente: ClienteRequest = {
        codigo: this.byClienteForm.get('documentType')?.value,
        numeroDocumento: this.byClienteForm.get('documentNumber')?.value.replace(/,/g, '')
      };
  
      const response = await firstValueFrom(this.clienteService.byCliente(cliente));
  
      if (!response) return;
  
      this.toastService.showToast(response.meta.message || 'Se encontró resultado', 'success');
      this.detalleCliente = response.data ?? new ClienteResponse();
      this.localStorage.save("detallesUsuario", this.detalleCliente);
      this.byClienteForm.reset();
      this.router.navigate(['/ResumenCliente']);
  
    }catch (error: any) {
      if (error?.response?.status === 404 || error?.status === 404) {
        this.toastService.showToast('No se encontraron registros', 'warning');
        return;
      }
      console.error(error);
      this.toastService.showToast('Hubo un error al buscar el usuario. Comunícate con el administrador', 'error');
      this.byClienteForm.reset();
    }
  }


  async getAllTipoDocumento(): Promise<void> {
    try {
      const response = await firstValueFrom(this.referenceDataService.getAllTipoDocumento());
  
      if (!response?.data?.length) {
        this.toastService.showToast('No se encontraron tipos de documento', 'warning');
        this.ListTipoDocumento = [];
        return;
      }
  
      this.ListTipoDocumento = response.data;
  
    } catch (error: any) {
      if (error?.response?.status === 404 || error?.status === 404) {
        this.toastService.showToast('No se encontraron tipos de documento', 'warning');
      } else {
        console.error('Error al obtener tipo documento:', error);
        this.toastService.showToast('Error desconocido, vuelva intentarlo', 'error');
      }
      this.ListTipoDocumento = [];
    }
  }

}
