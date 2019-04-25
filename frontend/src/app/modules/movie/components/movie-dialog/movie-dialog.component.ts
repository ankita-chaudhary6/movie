import { Component, OnInit, Inject } from '@angular/core';
import { Movie } from '../../movie';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { MovieService } from '../../movie.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {
movie:Movie;
comments:string;
actionType:string;
constructor(public snackBar:MatSnackBar, public dialogRef:MatDialogRef<MovieDialogComponent>,@Inject(MAT_DIALOG_DATA) public data:any,private service:MovieService) { 
  this.comments= data.obj.movieComments;
  this.movie=data.obj;
  this.actionType=data.actionType;
}
  ngOnInit() {
  }
  onNoClick(){
    this.dialogRef.close();
  }
  updateComments(){
    console.log("movie",this.movie);
    console.log("moviecomments"+this.movie.comments);
    this.dialogRef.close();
    this.service.updateComments(this.movie).subscribe(
      (movie)=>{
        this.snackBar.open("Movie updated to watchlist successfully","",{
          duration:2000,
        });
      }
    )
  }
}
