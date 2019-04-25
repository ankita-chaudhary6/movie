import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { AuthenticationService } from '../authentication/authentication.service';
import { MyOwnCustomMaterialModule } from 'src/app/my-own-custom-material.module';
import { AuthenticationRouterModule } from './authentication-routing.module';
import { BrowserModule } from '@angular/platform-browser';


@NgModule({
  declarations: [
    LoginComponent, 
    RegisterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MyOwnCustomMaterialModule,
    AuthenticationRouterModule,
    ReactiveFormsModule,
    BrowserModule
  ],
  providers: [AuthenticationService],
  exports: [
    AuthenticationRouterModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserModule

  ]
})
export class AuthenticationModule { }
