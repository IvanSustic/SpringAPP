import { Component } from '@angular/core';
import { ReviewService } from '../review.service';


import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';

@Component({
  selector: 'app-komentari',
  templateUrl: './komentari.component.html',
  styleUrl: './komentari.component.css'
})
export class KomentariComponent {
  Reviews: any;

  review: any;
  reviewFormGroup: FormGroup;
  newReview: any;

  constructor(public reviewServ: ReviewService,private fb: FormBuilder) {
      
    }


    ngOnInit() {
      this.reviewFormGroup = this.fb.group({
        naslov: ['', Validators.required],
        tekst: ['', Validators.required],
        ocjena: ['', Validators.required],
      });
      this.fetchReviews();
      
    }
  fetchReviews() {
    
    return this.reviewServ.getReviewsByVozilo(localStorage.getItem("username")).subscribe((res: {}) => {
      
      this.Reviews = res;
      
  
    });
  }

  onSubmit() {

    this.newReview = this.reviewFormGroup.value;
  

    if (this.reviewFormGroup.valid) {
    
     this.reviewServ.addReview(this.newReview,localStorage.getItem("username")).subscribe((res: {}) => {
       
      this.fetchReviews();
  
     });

    } else {

      this.reviewFormGroup.markAllAsTouched();
    }
  }

  deleteRev(naslov: string){
    this.reviewServ.deleteReview(naslov).subscribe((res: {})=>{
      
    });
    this.fetchReviews();
  }

 
}
