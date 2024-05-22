import { Component } from '@angular/core';
import { ReviewService } from '../review.service';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
@Component({
  selector: 'app-komentar',
  templateUrl: './komentar.component.html',
  styleUrl: './komentar.component.css'
})
export class KomentarComponent {
  naslov = this.actRoute.snapshot.params['naslov'];
  review: any;
  reviewFormGroup: FormGroup;
  newReview: any;
  constructor(public reviewServ: ReviewService,private fb: FormBuilder,public actRoute: ActivatedRoute) {
      
  }

  ngOnInit() {
    this.reviewFormGroup = this.fb.group({
      naslov: ['', Validators.required],
      tekst: ['', Validators.required],
      ocjena: ['', Validators.required],
    });
    this.fetchKomentar();
    
  }
  fetchKomentar() {
    
    return this.reviewServ.getReview(this.naslov).subscribe((res: {}) => {
      
      this.review = res;
      this.newReview = res;
      
      this.reviewFormGroup.setValue({
        naslov: this.newReview.naslov,
        tekst: this.newReview.tekst,
        ocjena: this.newReview.ocjena,
        
      })
  
    });
  }

  onSubmit() {

    this.newReview = this.reviewFormGroup.value;


    if (this.reviewFormGroup.valid) {

     this.reviewServ.changeReview(this.naslov,this.newReview).subscribe((res: {}) => {
       
   
  
     });

    } else {

      this.reviewFormGroup.markAllAsTouched();
    }
  }
}
