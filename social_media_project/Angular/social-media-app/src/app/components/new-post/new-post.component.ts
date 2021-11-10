import { Component, OnInit } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/post';
import { IUser } from 'src/app/models/user';
import { PostService } from 'src/app/services/post/post.service';
import { UserService } from 'src/app/services/user/user.service';

import { finalize } from 'rxjs/operators';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { FileUpload } from 'src/app/models/file-upload';


@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

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
  selectedFiles!: FileList;
  currentFileUpload!: FileUpload;
  percentage!: number;
  url!: string;

  constructor(private userService: UserService, 
    private posts: PostService, 
    private storage: AngularFireStorage, 
    public activeModal: NgbActiveModal, 
    public nav: NavbarService) { }

  ngOnInit(): void {
    this.nav.show();
    this.currentUser;
    this.getCurrentUser();
    this.unsubmittedContent = "";
    this.userSearch = "";
    // this.getPostsFromService();
    this.showUserInfo = false;
    this.searchedUser = { "email": "", "lastname": "", "firstname": "", "username": "" };
  }



  getCurrentUser() {
    if (this.userService.currUser !== null) {
      // console.log("from getCurr user " + this.userService.currUser);
      this.currentUser = this.userService.currUser;
      // console.log("curr user: " + this.currentUser.username);
    }
  }




  createPost() {
    this.showUserInfo = false;
    let newPost = new Post();
    newPost.poster = this.currentUser;
    // console.log("in createpost: " + JSON.stringify(newPost.poster));
    newPost.contents = this.unsubmittedContent;
    newPost.postURL = this.downloadURL;

    if (this.unsubmittedContent == "") {
      alert("Please enter a message before submitting.")
      return;
    }
    this.posts.addPost(newPost).subscribe(
      data => {
        // console.log(data);
      }, err => {
        // console.log("Error recieving posts");
      });
  }

  selectFile(eve:any): void {
    this.selectedFiles = eve.target.files;
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
          fileRef.getDownloadURL().subscribe((url: string) => {
            // console.log("url: " + url);
            this.downloadURL = url;
            this.postImage = url;
          });
        })
      ).subscribe()
    }
  }



}


