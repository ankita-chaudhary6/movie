import { Component } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import {  Router } from '@angular/router'

@Component({
  selector: 'app-root',
  template: `<mat-toolbar color="primary">
  <span>Movie Cruiser</span>
  <button mat-button [routerLink]="['/movies/popular']">Popular Movie</button>
  <button mat-button [routerLink]="['/movies/top-rated']">Top Rated Movie</button>
  <button mat-button [routerLink]="['/movies/watchlist']">WatchList</button>
  <button class="movie-search" mat-button [routerLink]="['/movies/search']">Search</button>
  <button mat-button (click)="logout()">Logout</button>
</mat-toolbar>
<router-outlet></router-outlet>`,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MovieCruiserFrontend';
  constructor(private authService:AuthenticationService,private router:Router){}
  
 logout() {
    this.authService.deleteToken();
   this.router.navigate(['/login']);
  }
}
