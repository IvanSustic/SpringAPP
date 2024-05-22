import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ShowSettingService {
  private showSubject = new BehaviorSubject<boolean>(false);

  setUpdateShow(){
      this.showSubject.next(!this.showSubject.getValue());
  }

  isShowing$(){
     this.showSubject.asObservable();
  }
}
