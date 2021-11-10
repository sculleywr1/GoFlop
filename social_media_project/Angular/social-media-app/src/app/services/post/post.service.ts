import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/post';
import { IUser } from 'src/app/models/user';

@Injectable()
export class PostService {
  private serveUrl: string =  `http://34.71.84.231:9005/socialMediaWebApp/api/`;

  constructor(private httpCli:HttpClient) {}

  addPost(newPost: Post){
    return this.httpCli.post<Post>(this.serveUrl + `post/createPost`, newPost, {withCredentials: true});
  }

  getPost(userId: number): Observable<Post>{
    return this.httpCli.get<Post>(this.serveUrl + `post/userPosts/${userId}`, {withCredentials: true});
  }

  getAllPost():Observable<Post> {
    return this.httpCli.post<Post>(this.serveUrl + `post/allPosts`, {withCredentials: true});
  }

  likedPost(updatePost: Post){
    // console.log("Post: "+ JSON.stringify(updatePost));
    return this.httpCli.post(this.serveUrl + `post/likePost`, updatePost, {withCredentials:true});
  }
  
}
