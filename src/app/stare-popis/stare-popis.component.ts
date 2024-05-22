import { Component } from '@angular/core';

import { VoziloServiceService } from '../vozilo-service.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { VoziloDTO } from '../vozilo-dto';
@Component({
  selector: 'app-stare-popis',
  templateUrl: './stare-popis.component.html',
  styleUrl: './stare-popis.component.css'
})
export class StarePopisComponent {
  Vozila: any = [];
  vozilo: any;
  registration: string;
  show: boolean = false;
  constructor(public vozilaServ: VoziloServiceService) {}
  ngOnInit() {
    
     this.fetchVozila();
    
    
  }

  fetchVozila() {
    
    return this.vozilaServ.getVozilaOld().subscribe((res: {}) => {
      this.Vozila = res;
      

    }); 


  }

  fetchVozilo(registration: string) {
    
    return this.vozilaServ.getVoziloDTO(registration).subscribe((res: {}) => {
      
      this.vozilo = res;
      this.show = true;
  
    });
  }
}
