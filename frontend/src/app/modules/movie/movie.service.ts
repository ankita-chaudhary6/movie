import { Injectable } from '@angular/core';
import  { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Movie } from './movie';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  apikey: string;
  tmdbEndpoint: string ; 
  imagePrefix: string;
  watchlistEndpoint: string;
  springEndPoint: string;
  search: string;
  constructor(private http:HttpClient) { 
    this.tmdbEndpoint="https://api.themoviedb.org/3/movie";
   this.imagePrefix="https://image.tmdb.org/t/p/w500"; 
   this.apikey="api_key=55c788c0b112e12dd6591201e453b937";
   this.watchlistEndpoint="http://localhost:3000/watchlist";
   this.springEndPoint="http://localhost:8082/api/movie";
   this.search="https://api.themoviedb.org/3/search/movie?";
  }

  getMovies(type: string, page: number=1):Observable<Array<Movie>>{
    const endpoint= `${this.tmdbEndpoint}/${type}?${this.apikey}&page=${page}`;
   return this.http.get(endpoint).pipe(
     map(this.pickMovieResults),
     map(this.transformPosterPath.bind(this))
   );
   
  }
  transformPosterPath(movies): Array<Movie>{
    return movies.map(movie=>{
      movie.poster_path=`${this.imagePrefix}${movie.poster_path}`;
      return movie;
    });
  }
  pickMovieResults(response){
    return response['results'];
  }

  addMovieToWatchList(movie){
    console.log("in services "+movie);
    return this.http.post(this.springEndPoint,movie);
  }

  getWatchlistedMovies(): Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.springEndPoint);
  }
  deleteFromWatchlist(movie){
    const url=`${this.springEndPoint}/${movie.id}`;
    return this.http.delete(url,{responseType: 'text'});
  }
  updateComments(movie)
  {
    return this.http.put(this.springEndPoint,movie);

  }
  searchMovies(searchKey:string, page:number=1): Observable<Array<Movie>>{
    if(searchKey.length>0)
    {
      const url=`${this.search}${this.apikey}&page=${page}&include_adult=true&query=${searchKey}`;
      return this.http.get(url).pipe(
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );
    }
  }

}