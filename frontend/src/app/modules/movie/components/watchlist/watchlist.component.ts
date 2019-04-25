import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies:Array<Movie>;
  useWatchlistApi = true;

  constructor(private movieService:MovieService, private snackBar:MatSnackBar) { 
    this.movies=[];
  }

  ngOnInit() {
    let message = 'watchlist  is empty';
    this.movieService.getWatchlistedMovies().subscribe((movies) => {
      if (movies === null ) {
        this.snackBar.open(message, '', {
          duration: 1000
        });
      }
      this.movies.push(...movies);
    })
  }


}
