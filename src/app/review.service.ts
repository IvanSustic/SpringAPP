import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Review } from './review';
import { Observable,throwError } from 'rxjs';
import { retry,catchError, tap } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  baserurl= "http://localhost:8080/review"
  constructor(private http:HttpClient) {
    
   }
   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
  tokenType  = 'Bearer ';
  getReviews(): Observable<Review> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http.get<Review>(this.baserurl+"/all",headers)
.pipe(retry(1),catchError(this.errorHandl));


  }

  getReview(naslov: any): Observable<any> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http
      .get<any>(this.baserurl + '/find/' + naslov,headers)
      .pipe(retry(1), catchError(this.errorHandl));
  }


  logout(): Observable<Review> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http.post<Review>("http://localhost:8080/auth/api/v1/logout",headers)
.pipe(retry(1),catchError(this.errorHandl));


  }

  getReviewsByVozilo(registracija: any): Observable<Review> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http
      .get<Review>(this.baserurl + '/user/' + registracija,headers)
      .pipe(retry(1), catchError(this.errorHandl));
  }


  addReview(review: any,username: string): Observable<any> {

    return this.http.post<any>(this.baserurl+"/add/"+username, review).pipe(retry(1), catchError(this.errorHandl));
    
    }

    
  changeReview(naslov: any,review: any): Observable<any> {
 
  
    return this.http.put<any>(this.baserurl+"/change/"+naslov, review).pipe(retry(1), catchError(this.errorHandl));
    
    }

    

    deleteReview(naslov: string): Observable<any> {
  
      return this.http.delete<any>(this.baserurl+"/delete/"+naslov).pipe(retry(1), catchError(this.errorHandl));
      
      }


  errorHandl(err: any) {
    let message = '';
    if (err.error instanceof ErrorEvent) {
      message = err.error.message;
    } else {
      message = `Error Code: ${err.status}\nMessage: ${err.message}`;
    }
    console.log(message);
    return throwError(() => {
      message;
    });

}
}
