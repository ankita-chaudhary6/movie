import { Component, OnInit,Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService} from '../../movie.service'
import { ActivatedRoute, Router } from '@angular/router'
import { MatSnackBar } from '@angular/material';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  movies:Array<Movie>;
  @Input()
  useWatchlistApi: boolean;
  constructor(private authService: AuthenticationService,private movieService: MovieService,private snackBar: MatSnackBar,private router: Router) {
   
   }

  ngOnInit() {
  
  }
  addToWatchlist(movie){
   let message=`${movie.title} add to watchlist`;
   console.log("container "+movie);
   
    this.movieService.addMovieToWatchList(movie).subscribe(()=>{
      this.snackBar.open(message,'',{
        duration:1000
      });
  
    });
  }
  deleteFromWatchlist(movie){
    let message=`${movie.title} deleted from watchlist`;
    for(var i=0;i<this.movies.length;i++)
    {
      if(this.movies[i].title === movie.title)
      {
        this.movies.splice(i,1)
      }
    }
    this.movieService.deleteFromWatchlist(movie).subscribe((movie)=>{
      this.snackBar.open(message,'',{
        duration:1000
    })
  })
}


}
