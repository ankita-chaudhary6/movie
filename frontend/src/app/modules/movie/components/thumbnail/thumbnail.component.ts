import { Component, OnInit,Input, Output ,EventEmitter} from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service'
import { MatDialog  } from '@angular/material';
import { EmitterVisitorContext } from '@angular/compiler';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
 @Input()
  movie:Movie;
 @Input()
 useWatchlistApi: boolean;
 @Output()
  addMovie = new EventEmitter();
@Output()
deleteMovie= new EventEmitter();
@Output()
updateMovie=new EventEmitter();

  constructor(private dailog:MatDialog) { 
  }
  
  ngOnInit() {
  
  }
addToWatchlist(){
  this.addMovie.emit(this.movie);
  console.log(this.movie);

}
deleteFromWatchlist(){
this.deleteMovie.emit(this.movie);
}
updateWatchlist(actionType)
{
let dialogRef = this.dailog.open(MovieDialogComponent,{
  width: '400px',
  data: { obj: this.movie, actionType:actionType }
});
dialogRef.afterClosed().subscribe(result =>{
  console.log("dialog was closed");
})
}
}

