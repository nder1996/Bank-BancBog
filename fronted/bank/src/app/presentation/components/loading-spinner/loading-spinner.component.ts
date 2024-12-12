import { Component } from '@angular/core';
import { LoadingService } from 'src/service/loading.service';



@Component({
  selector: 'app-loading-spinner',
  templateUrl: './loading-spinner.component.html',
  styleUrls: ['./loading-spinner.component.css']
})
export class LoadingSpinnerComponent {

  loading$ = this.spinnerService.loading$;
  

  constructor(private spinnerService: LoadingService){
  }


  public isSpinnerVisible:boolean = false;

  ngOnInit(): void {
    this.spinnerService.getSpinnerState().subscribe((state: boolean) => {
      this.isSpinnerVisible = state;
    });
  }
}
