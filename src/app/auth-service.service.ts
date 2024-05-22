import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, tap, throwError} from "rxjs";
@Injectable({
providedIn: 'root'
})
export class AuthServiceService {
private loginUrl = 'http://localhost:8080/auth/api/v1/login';
private logoutUrl = 'http://localhost:8080/auth/api/v1/logout';
private registerUrl = 'http://localhost:8080/auth/api/v1/register';
private refreshTokenUrl = 'http://localhost:8080/auth/api/v1/refreshToken';
constructor(private http: HttpClient) { }
httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};
login(user: any): Observable<any> {
return this.http.post<any>(this.loginUrl, user, this.httpOptions ).pipe(
tap(response => {
console.log(response)

localStorage.setItem('token', response.accessToken);
localStorage.setItem('refreshToken', response.token);
console.log("Access token", response.accessToken);
console.log("refresh token", response.token)

  })
  );
  }
  logout(): Observable<any> {
  
  return this.http.post<any>(`${this.logoutUrl}`,localStorage.getItem("refreshToken") );
  }
  register(username: string, email: string, password: string): Observable<any>{
  return this.http.post<any>(`${this.registerUrl}`, { username, email, password });
  }
  refreshToken(): Observable<any> {
  const refreshToken = localStorage.getItem('refreshToken');
  if (!refreshToken) {
  console.error('No refresh token found');
  return throwError(() => new Error('No refresh token found'));
  }
  const body = { refreshToken: refreshToken, expiredAccessToken: localStorage.getItem('token') };
return this.http.post<any>(`${this.refreshTokenUrl}`, body).pipe(
tap(response => {
if (response && response.tokens) {
localStorage.setItem('token', response.accessToken);
localStorage.setItem('refreshToken', response.token);
}
})
);
}
}