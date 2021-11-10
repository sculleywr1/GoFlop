declare var require: any;

import { Component, OnInit } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/post';
import { IUser } from 'src/app/models/user';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { PostService } from 'src/app/services/post/post.service';
import { ProfileService } from 'src/app/services/profile-service/profile.service';
import { SearchUserService } from 'src/app/services/search-user/search-user.service';
import { UserService } from 'src/app/services/user/user.service';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser!: IUser;
  showUserInfo!: boolean;
  chosenUser!: IUser;
  chosenUserName!: String;
  postList: Post[] = [];
  chosenUserLevel!: number;
  chosenUserClass!: String;
  classes: string[] = ['fighter', 'wizard', 'sorceror', 'barbarian', 'cleric', 'ranger', 'druid', 'paladin']
  userSearch!: string;

  Filter = require('bad-words')
  filter = new this.Filter();

  constructor(
    private profileService: ProfileService,
    private userService: UserService,
    private posts: PostService,
    private storage: AngularFireStorage,
    public nav: NavbarService,
    private editModal: NgbModal,
    private userSearchServ: SearchUserService) { }

  ngOnInit(): void {
    this.filter.addWords('some', 'bad', 'word');
    this.nav.show();
    this.currentUser;
    this.getCurrentUser();
    this.getPostsFromService();
    this.chosenUser;
    this.chosenUserName;
  }

  getChosenUser() {
    if (this.userService.currUser !== null) {
      this.profileService.getUserById(this.chosenUserName)
    }
  }

  getCurrentUser() {
    if (this.userService.currUser !== null) {
      // console.log("from getCurr user " + this.userService.currUser);
      this.currentUser = this.userService.currUser;
      // console.log("curr user: " + this.currentUser.username);
    }
  }

  populatePostList(obs: Observable<any>) {
    obs.subscribe(
      data => {
        // console.log("form pop: " + JSON.stringify(data));
        let list;
        try {
          list = JSON.parse(data.body);
        }
        catch (e) {
          list = data;
        }
        list.sort((a: { likes: number; }, b: { likes: number; }) => (a.likes > b.likes ? 1 : -1));
        this.postList = [];

        for (let i = 0; i < list.length; i++) {

          let newPost = new Post();
          newPost.poster = list[i].poster;
          newPost.contents = list[i].contents;
          newPost.postId = list[i].postId;
          newPost.likes = list[i].likes;
          newPost.postURL = list[i].postURL;
          newPost.postDate = list[i].postDate;

          newPost.initPos();


          this.postList.unshift(newPost);
        }
      }, () => {
        console.error;
      });
  }

  getPostsFromService() {
    this.postsFromService(this.currentUser.userId);
  }

  postsFromService(id: number) {
    this.populatePostList(this.posts.getPost(id));
  }


  editProfile() {
    const modalRef = this.editModal.open(EditProfileComponent);
  }

  likePost(post: Post) {
    console.log("posty: " + JSON.stringify(post));
    let updatedPost = new Post();
    updatedPost.postId = post.postId;
    updatedPost.contents = post.contents;
    updatedPost.likes = post.likes + 1;
    updatedPost.postDate = post.postDate;
    updatedPost.postURL = post.postURL;
    updatedPost.poster = post.poster;
    this.posts.likedPost(updatedPost).subscribe();
    this.populatePostList(this.posts.getPost(post.poster.userId))
  }

  searchForUser() {
    // console.log(this.userSearch);
    this.userSearchServ.getUserByName(this.userSearch).subscribe(
      data => {
        // console.log(JSON.stringify(data));
        this.currentUser.username = data.username;
        this.currentUser.firstName = data.firstName;
        this.currentUser.lastName = data.lastName;
        this.currentUser.userEmail = data.userEmail;
        this.currentUser.profilePicURL = data.profilePicURL;
        this.populatePostList(this.posts.getPost(data.userId));
      }
    )
  }

}
