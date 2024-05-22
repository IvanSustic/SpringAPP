import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders, HttpParams } from '@angular/common/http';
import { VoziloDTO } from './vozilo-dto';
import { Vozilo } from './vozilo';
import { Observable,throwError } from 'rxjs';
import { retry,catchError, tap } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class VoziloServiceService {
  baserurl= "http://localhost:8080/voziloStore"
  constructor(private http: HttpClient) {}
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+localStorage.getItem("token")
    }),
  };
  tokenType  = 'Bearer ';
  getVozila(): Observable<VoziloDTO> {
    console.log(localStorage.getItem("token"))
    console.log(localStorage.getItem("refreshToken"))
    console.log(localStorage.getItem("username"))
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http.get<VoziloDTO>(this.baserurl+"/all",headers)
.pipe(retry(1),catchError(this.errorHandl));


  }

  getVozilaFilterd(fuel: string | number | boolean, reg:string | number | boolean): Observable<VoziloDTO> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    const params = new HttpParams()
   .set('f', fuel)
   .set('p', reg);
    return this.http.get<VoziloDTO>(this.baserurl+"/filtered",{params})
.pipe(retry(1),catchError(this.errorHandl));


  }

  getVozilaOld(): Observable<VoziloDTO> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http.get<VoziloDTO>(this.baserurl+"/old",headers)
.pipe(retry(1),catchError(this.errorHandl));


  }

  getVoziloDTO(registracija: any): Observable<VoziloDTO> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http
      .get<VoziloDTO>(this.baserurl + '/vozilo/registracija/' + registracija,headers)
      .pipe(retry(1), catchError(this.errorHandl));
  }

  getVoziloCode(registracija: any): Observable<VoziloDTO> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http
      .get<VoziloDTO>(this.baserurl + '/vozilo/' + registracija,headers)
      .pipe(retry(1), catchError(this.errorHandl));
  }

  addVozilo(vozilo: any): Observable<VoziloDTO> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http.post<VoziloDTO>(this.baserurl+"/vozilo", vozilo, this.httpOptions).pipe(retry(1), catchError(this.errorHandl));
    
    }

    
  changeVozilo(vozilo: any): Observable<VoziloDTO> {
    const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
    const headers = { headers: header };
    
    return this.http.put<VoziloDTO>(this.baserurl+"/voziloChange", vozilo, this.httpOptions).pipe(retry(1), catchError(this.errorHandl));
    
    }

    

    deleteVozilo(registracija: string): Observable<VoziloDTO> {
      const header = new HttpHeaders().set('Authorization', this.tokenType + localStorage.getItem("token")); // may be localStorage/sessionStorage
      const headers = { headers: header };
      
      return this.http.delete<VoziloDTO>(this.baserurl+"/vozilo/"+registracija,  this.httpOptions).pipe(retry(1), catchError(this.errorHandl));
      
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
