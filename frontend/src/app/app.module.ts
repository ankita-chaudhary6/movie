import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MovieModule } from './modules/movie/movie.module';
import { MyOwnCustomMaterialModule } from './my-own-custom-material.module'
import { FormsModule } from '@angular/forms';
import { AuthenticationModule } from '../app/modules/authentication/authentication.module';
import { AuthGuardService } from 'src/app/auth-guard.service';


const appRoutes: Routes = [
  {
    path:'',
    redirectTo: '/login',
    pathMatch: 'full',
  }
]
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MovieModule,
    FormsModule,
    MyOwnCustomMaterialModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes),
  ],
  
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
