import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PopisComponent } from './popis/popis.component';
import { InfoComponent } from './info/info.component';
import { StarePopisComponent } from './stare-popis/stare-popis.component';
import { LoginComponent } from './login/login.component';
import { KomentariComponent } from './komentari/komentari.component';
import { AuthGuardGuard } from './auth-guard.guard';
import { AuthGuardGuardService } from './auth-guard-guard.service';
import { PopisUserComponent } from './popis-user/popis-user.component';
import { PopisInfoUserComponent } from './popis-info-user/popis-info-user.component';
import { NavbarComponent } from './navbar/navbar.component';
import { KomentarComponent } from './komentar/komentar.component';
const routes: Routes = [
  {path:'', pathMatch: 'full', redirectTo:'login'},
  {path:'komentari', component:KomentariComponent},
  {path:'login', component:LoginComponent},
  {path:'popis', component:PopisComponent,  canActivate: [AuthGuardGuardService]},
  {path:'vozilo/:registration',  component:InfoComponent , canActivate: [AuthGuardGuardService]},
  {path:'old',  component:StarePopisComponent},
  {path:'popisUser',  component:PopisUserComponent},
  {path:'voziloUser/:registration',  component:PopisInfoUserComponent },
  {path:'navbar',  component:NavbarComponent },
  {path:'review/:naslov',  component:KomentarComponent },
];  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
