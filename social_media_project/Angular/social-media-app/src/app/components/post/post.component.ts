declare var require: any;

import { Component, OnInit } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { Post } from 'src/app/models/post';
import { IUser } from 'src/app/models/user';
import { PostService } from 'src/app/services/post/post.service';
import { UserService } from 'src/app/services/user/user.service';
import { finalize } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  currentUser!: IUser;
  showCommentEntry!: boolean;
  showUserInfo!: boolean;
  unsubmittedContent!: string;
  postImage: any;
  // base path for firebase upload
  private basePath = '/uploads';
  uploadPercent!: Observable<number | undefined>;
  downloadURL: string = "";
  postList: Post[] = [];
  searchedUser: any;
  userSearch!: string;
  Filter = require('bad-words')
  filter = new this.Filter();


  constructor(private userService: UserService, private posts: PostService, private storage: AngularFireStorage) { }

  ngOnInit(): void {
    this.filter.addWords('some', 'bad', 'word');
    this.currentUser;
    this.getCurrentUser();
    this.unsubmittedContent = "";
    this.userSearch = "";
    this.getPostsFromService();
    this.showUserInfo = false;
    this.searchedUser = {"email":"", "lastname":"", "firstname":"", "username":""};
  }

  getPostsFromService() {
    this.postsFromService();
    this.showUserInfo = false;
}

  getCurrentUser() {
    if(this.userService.currUser !== null) {
      // console.log("from getCurr user " + this.userService.currUser);
      this.currentUser = this.userService.currUser;
      // console.log("curr user: " + this.currentUser.username);
    }
  }


  postsFromService() {
    this.populatePostList(this.posts.getAllPost());
  }
  populatePostList(obs: Observable<any>) {
    obs.subscribe(
      data => {
          // console.log("post data "+ data);
          let list;
          try {
              list = JSON.parse(data.body);
          } catch (e) {
              //try catch to test if data is a JSON or not
              //will throw error when getting back a JSON from addPost
              //catches it and sets list equal to list of JSON
              list = data;
              
          }
          list.sort((a: { likes: number; }, b: { likes: number; }) => (a.likes > b.likes ? 1 : -1));
          // console.log("list: "+ JSON.stringify(list));
          // if (list == null) {
          //     console.log("Error: Feed Componenet: populatePostList response body error");
          // }

          this.postList = [];

          for (let i = 0; i < list.length; i++) {
              let newPost = new Post();
              newPost.poster = list[i].poster;
              newPost.contents = list[i].contents;
              newPost.postId = list[i].postId;
              newPost.likes = list[i].likes;
              newPost.postURL = list[i].postURL;
              newPost.postDate = list[i].postDate;

              this.postList.unshift(newPost);
          }
      },
      () => {
          // console.error;
          // console.log("error: feed componenet: response error");
      }
  );
  }

  createPost() {
    this.showUserInfo = false;
    let newPost = new Post();
    newPost.poster = this.currentUser;
    // console.log("in createpost: " + JSON.stringify(newPost.poster));
    newPost.contents = this.unsubmittedContent;
    newPost.postURL = "";

    if (this.unsubmittedContent == "") {
      alert("Please enter a message before submitting.")
      return;
    }



    this.posts.addPost(newPost).subscribe(
      data => {
        // console.log(data);
        this.postsFromService();
      }, err => {
        // console.log("Error recieving posts");
      });

  }



  onFileChange(eve: any) {
    if (eve.target.files && eve.target.files.length > 0) {
      let file = eve.target.files[0];
      const filePath = `${this.basePath}/${file.name}`;
      // console.log(filePath);
      const fileRef = this.storage.ref(filePath);
      // const task = fileRef.put(file);
      const task = this.storage.upload(filePath, file);
      // console.log(task + " | " + filePath);

      // observe percentage changes
      this.uploadPercent = task.percentageChanges();
      // get notified when the download URL is available
      task.snapshotChanges().pipe(
        finalize(() => {
        fileRef.getDownloadURL().subscribe((url: string) =>{
          // console.log("url: " + url);
          this.downloadURL = url;
        });
        })
      ).subscribe()
    }
  }

  likePost(post:Post) {
    let updatedPost = new Post();
    updatedPost.postId = post.postId;
    updatedPost.contents = post.contents;
    updatedPost.likes = post.likes+1;
    updatedPost.postDate = post.postDate;
    updatedPost.postURL = post.postURL;
    updatedPost.poster = post.poster;
  this.posts.likedPost(updatedPost).subscribe();
  this.getPostsFromService();
  }
}
