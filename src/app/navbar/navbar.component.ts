import { Component } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(public service: AuthServiceService) {
      
  }

  logout(){
    return this.service.logout().subscribe((res: {}) => {
      
    
  
    });
  }
}
