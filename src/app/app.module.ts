import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PopisComponent } from './popis/popis.component';
import { InfoComponent } from './info/info.component';
import { VoziloServiceService } from './vozilo-service.service';
import { SortPipe } from './sort.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StarePopisComponent } from './stare-popis/stare-popis.component';
import { VozilaSortPipe } from './vozila-sort.pipe';
import { LoginComponent } from './login/login.component';
import { KomentariComponent } from './komentari/komentari.component';
import { AuthInterceptor } from './auth-interceptor.service';
import { NavbarComponent } from './navbar/navbar.component';
import { PopisUserComponent } from './popis-user/popis-user.component';
import { PopisInfoUserComponent } from './popis-info-user/popis-info-user.component';
import { KomentarComponent } from './komentar/komentar.component';
@NgModule({
  declarations: [
    AppComponent,
    PopisComponent,
    InfoComponent,
    SortPipe,
    StarePopisComponent,
    VozilaSortPipe,
    LoginComponent,
    KomentariComponent,
    NavbarComponent,
    PopisUserComponent,
    PopisInfoUserComponent,
    KomentarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
        ReactiveFormsModule,
        BrowserModule,
        
        ReactiveFormsModule,
        HttpClientModule,
        FormsModule
        
  ],
  providers: [VoziloServiceService, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
