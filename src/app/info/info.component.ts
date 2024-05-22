import { Component } from '@angular/core';
import { VoziloDTO } from '../vozilo-dto';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { VoziloServiceService } from '../vozilo-service.service';
import { ReviewService } from '../review.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { Review } from '../review';
@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrl: './info.component.css'
})
export class InfoComponent {

  registration = this.actRoute.snapshot.params['registration'];
  vozilo: any;
  carForm: FormGroup;
  newVozilo: any;

  constructor(public vozilaServ: VoziloServiceService,public actRoute: ActivatedRoute,
    public router: Router,private fb: FormBuilder, public reviewServ: ReviewService) {
      
    }
  ngOnInit() {
    this.carForm = this.fb.group({
 
      maxPassengers: ['', Validators.required],
      gearBox: ['', Validators.required],
      doors: ['', Validators.required],
      fuel: ['', Validators.required],
      mileage: ['', Validators.required],
      registration: ['', Validators.required],
      chassisNumber: ['', Validators.required],
      airConditioning: [false]
    });

    this.fetchVozilo();
    
    
  }
  fetchVozilo() {
    
    return this.vozilaServ.getVoziloDTO(this.registration).subscribe((res: {}) => {
      
      this.vozilo = res;
      this.newVozilo = res;
      console.log(this.newVozilo)
      this.carForm.setValue({
        maxPassengers: this.newVozilo.maxPassengers,
        gearBox: this.newVozilo.gearBox,
        doors: this.newVozilo.doors,
        fuel: this.newVozilo.fuel,
        mileage: this.newVozilo.mileage,
        registration: this.newVozilo.registration,
        chassisNumber: this.newVozilo.chassisNumber,
        airConditioning: this.newVozilo.airConditioning
      })
  
    });
  }


  
  onSubmit() {

    this.newVozilo = this.carForm.value;

    this.newVozilo.lastInspection="10.10.2020.";
    this.newVozilo.nextInspection="10.10.2025.";

    console.log(this.carForm.value)
    if (this.carForm.valid) {

     this.vozilaServ.changeVozilo(this.newVozilo).subscribe((res: {}) => {
       
      this.fetchVozilo();
  
     });

    } else {

      this.carForm.markAllAsTouched();
    }
  }
}
