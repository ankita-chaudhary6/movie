import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MovieService } from './movie.service';
import { MyOwnCustomMaterialModule } from '../../my-own-custom-material.module'
import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie.router';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptorService } from './token-interceptor.service';
@NgModule({
  declarations: [ ThumbnailComponent, ContainerComponent, WatchlistComponent, TmdbContainerComponent, MovieDialogComponent, SearchComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    FormsModule,
    MyOwnCustomMaterialModule,
    ReactiveFormsModule
  ],
  exports: [
    
    ThumbnailComponent,
    WatchlistComponent,
    TmdbContainerComponent,
    MovieRouterModule,
    MovieDialogComponent,
    MyOwnCustomMaterialModule,
    SearchComponent
  ],
  entryComponents:[MovieDialogComponent],
  providers: [
    MovieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]

})
export class MovieModule { }
