import { Component } from '@angular/core';

import { VoziloServiceService } from '../vozilo-service.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { VoziloDTO } from '../vozilo-dto';
@Component({
  selector: 'app-popis-user',
  templateUrl: './popis-user.component.html',
  styleUrl: './popis-user.component.css'
})
export class PopisUserComponent {
  Vozila: any = [];
  vozilo: any;
  carForm: FormGroup;
  newVozilo: any;
  reg: string = "";
  fuel: string = "";
  constructor(public vozilaServ: VoziloServiceService,private fb: FormBuilder) {}
  ngOnInit() {
    
     this.fetchVozila();
    
  }



  fetchVozila() {
    
    return this.vozilaServ.getVozila().subscribe((res: {}) => {
      this.Vozila = res;
   

    }); 
  }

  

  filter(){
    console.log(this.fuel)
    return this.vozilaServ.getVozilaFilterd(this.fuel,this.reg).subscribe((res: {}) => {
      this.Vozila = res;
   

    }); 
   
  }
}
