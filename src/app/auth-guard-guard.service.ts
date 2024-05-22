import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { Subject } from 'rxjs'
import { Observable } from 'rxjs';

import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
@Injectable({
providedIn: 'root'
})
export class AuthGuardGuardService implements CanActivate {
constructor(
private router: Router,
private userService: UserService,
) { }


canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
return this.checkLogin();
}
checkLogin(): Observable<boolean> {
  var subject = new Subject<boolean>();
  this.userService.auth(localStorage.getItem("username")).subscribe((res: {}) => {
       console.log(res)
    if(res == "ADMIN"){
      subject.next(true);
      
    } else {
      subject.next(false);
    }
    
   });
   return subject.asObservable();
  }
}