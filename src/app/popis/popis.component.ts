import { Component } from '@angular/core';
import { VoziloServiceService } from '../vozilo-service.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { VoziloDTO } from '../vozilo-dto';
@Component({
  selector: 'app-popis',
  templateUrl: './popis.component.html',
  styleUrl: './popis.component.css'

})
export class PopisComponent {
  Vozila: any = [];
  vozilo: any;
  carForm: FormGroup;
  newVozilo: any;
  reg: string = "";
  fuel: string = "";
  constructor(public vozilaServ: VoziloServiceService,private fb: FormBuilder) {}
  ngOnInit() {
    
     this.fetchVozila();
     this.carForm = this.fb.group({
      code: ['', Validators.required],
      maxPassengers: ['', Validators.required],
      gearBox: ['', Validators.required],
      doors: ['', Validators.required],
      fuel: ['', Validators.required],
      mileage: ['', Validators.required],
      registration: ['', Validators.required],
      chassisNumber: ['', Validators.required],
      airConditioning: [false]
    });
    console.log(localStorage.getItem("token"));
  }



  fetchVozila() {
    
    return this.vozilaServ.getVozila().subscribe((res: {}) => {
      this.Vozila = res;
   

    }); 
  }

  onSubmit() {

    this.newVozilo = this.carForm.value;

    this.newVozilo.lastInspection="10.10.2020.";
    this.newVozilo.nextInspection="10.10.2025.";

    console.log(this.carForm.value)
    if (this.carForm.valid) {

     this.vozilaServ.addVozilo(this.newVozilo).subscribe((res: {}) => {
       
      this.fetchVozila();
  
     });

    } else {

      this.carForm.markAllAsTouched();
    }
  }

  deleteCar(registracija: string){
    this.vozilaServ.deleteVozilo(registracija).subscribe((res: {})=>{
      
    });
    this.fetchVozila();
  }

  filter(){
    console.log(this.fuel)
    return this.vozilaServ.getVozilaFilterd(this.fuel,this.reg).subscribe((res: {}) => {
      this.Vozila = res;
   

    }); 
   
  }
  
}
