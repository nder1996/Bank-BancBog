import { Component } from '@angular/core';
import { NotificationToastService, Toast } from 'src/service/notification-toast.service';
import { timer } from 'rxjs';
@Component({
  selector: 'app-notificacion-toast',
  templateUrl: './notificacion-toast.component.html',
  styleUrls: ['./notificacion-toast.component.css']
})
export class NotificacionToastComponent {
  toasts: Toast[] = [];

  constructor(private toastService: NotificationToastService) {}

  ngOnInit() {
    this.toastService.toast$.subscribe((toast) => {
      this.toasts.push(toast);
      timer(3000).subscribe(() => this.removeToast(toast));
    });
  }

  removeToast(toast: Toast) {
    this.toasts = this.toasts.filter((t) => t !== toast);
  }

}
