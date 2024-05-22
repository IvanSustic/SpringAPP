import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Review } from './review';
import { Observable,throwError } from 'rxjs';
import { retry,catchError, tap } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  baserurl= "http://localhost:8080/auth/api/v1"
  constructor(private http:HttpClient) {
    
   }
   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
 
  auth(user: any): Observable<any> {

    return this.http.get(this.baserurl+"/user/"+ user, {responseType: 'text'}).pipe(retry(1), catchError(this.errorHandl));
    
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
