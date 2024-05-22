import { Component } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { NotificationServiceService } from '../notification-service.service';
import { tick } from '@angular/core/testing';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError: string = '';
  constructor(
  private authService: AuthServiceService,
  private router: Router,
  private fb: FormBuilder,
  private notificationService: NotificationServiceService 
  )
  {
    this.loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
    });
    }
    login() {
    this.loginError = '';
    this.authService.login(this.loginForm.value).subscribe({
    next: () => {
    localStorage.setItem('username', this.loginForm.get('username')?.value);
    this.notificationService.authentificationMessageSuccess("Logged in!", "Successfully logged in redirecting to home..");
   
    },
    error: (error) => {
    console.error('Login failed', error);
    this.router.navigate(['/login']);
    this.loginError = 'Invalid email or password';
    }
    });
    }
    }