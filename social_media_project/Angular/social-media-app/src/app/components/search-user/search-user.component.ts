import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/post';
import { IUser } from 'src/app/models/user';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { PostService } from 'src/app/services/post/post.service';
import { ProfileService } from 'src/app/services/profile-service/profile.service';
import { SearchUserService } from 'src/app/services/search-user/search-user.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {
  currentUser!: IUser;
  postList: Post[] = [];
  userSearch!: string;

  constructor( 
    private profileService: ProfileService, 
    private userService: UserService, 
    private posts: PostService,
    public nav: NavbarService,
    private userSearchServ: SearchUserService) { }

  ngOnInit(): void {
    this.nav.show();
  }

  populatePostList(obs:Observable<any>){
    obs.subscribe(
      data => {
        let list;
        try
        {
          list = JSON.parse(data.body);
        }
        catch (e)
        {
          list = data;
        }

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


          this.postList.push(newPost);
        }
      }, () => {
        // console.error;
      });
  }

  postsFromService(id: number) {
    this.populatePostList(this.posts.getPost(id));
  }

  searchForUser(){
    // console.log(this.userSearch);
    this.userSearchServ.getUserByName(this.userSearch).subscribe(
      data=> {
        // console.log(JSON.stringify(data));
        this.currentUser.username=data.username;
        this.currentUser.firstName = data.firstName;
        this.currentUser.lastName = data.lastName;
        this.currentUser.userEmail = data.userEmail;
        this.postsFromService(data.userId);
      }
    )
  }

}
