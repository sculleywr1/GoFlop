import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { finalize } from 'rxjs/operators';
import { IUser } from 'src/app/models/user';
import { UpdateUserService } from 'src/app/services/update-user/update-user.service';
import { UserService } from 'src/app/services/user/user.service';
import { FileUpload } from 'src/app/models/file-upload';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  private basePath = '/uploads';
  currentFileUpload!: FileUpload;
  uploadPercent!: Observable<number | undefined>;
  downloadURL: string = "";
  editUser: IUser = new IUser();
  new_password1!: string;
  new_password2!: string;
  new_img: any;
  curr_password!: string;

  constructor(
    public activeModal: NgbActiveModal,
    private currUserServ: UserService,
    private updateService: UpdateUserService,
    private storage: AngularFireStorage) { }

  ngOnInit(): void {
    // console.log("cus " + this.currUserServ.currUser.userEmail)
    this.editUser = this.currUserServ.currUser
  }

  confirmChanges() {
    // console.log("curr user pass: "+this.editUser.password)
    if (this.new_password1 === this.new_password2) {
      // console.log("new pw matched!");
      this.updateUser();
      this.activeModal.close('Close');
    }
    else {
     console.log("wrong password or new passwords do not match");
    }
  }


  updateUser(): void {
    // console.log("Updating user information!");
    if(this.curr_password == null) {
      return;
    }
    if (this.new_password1 != null) {
      this.editUser.password = this.new_password1;
    } else {
      this.editUser.password = this.curr_password;
    }

    this.editUser.profilePicURL = this.new_img;
    this.updateService.update(this.editUser).subscribe(
      (data: any) => {
        // console.log("update user successfully!");
        //update curUser
        // console.log(this.editUser);
        this.currUserServ.currUser = this.editUser;
        window.location.reload();
      });
  }

  onFileChange(eve: any) {
    if (eve.target.files && eve.target.files.length > 0) {
      let file = eve.target.files[0];
      const filePath = `${this.basePath}/${file.name}`;
      console.log(filePath);
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
            this.new_img = url;
          });
        })
      ).subscribe()
    }

  }
}