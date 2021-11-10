import { IUser } from "./user";

export class Post {
    
    postId!: number;
    poster!: IUser;
	postDate!: Date; 
    contents!: string;
	likes!: number;
    postURL!: string; 
	position!: number[];
    constructor( ) {}
    
	public get _postId(): number {
		return this.postId;
	}

	public set _postId(postId: number) {
		this.postId = postId;
    }
    

	public get _poster(): IUser {
		return this.poster;
	}

	public set _poster(poster: IUser) {
		this.poster = poster;
	}

	public get _content(): string {
		return this.contents;
	}

	public set _content(content: string) {
		this.contents = content;
	}

	public get _likes(): number {
		return this.likes;
	}

	public set _likes(likes: number) {
		this.likes = likes;
	}
 
	
	public get _image(): string {
		return this.postURL;
	}

	public set _image(postURL: string) {
		this.postURL = postURL;
	}

	public get _postDate(): Date {
		return this.postDate;
	}

	public set _postDate(postDate: Date) {
		this.postDate = postDate;
	}

	public initPos(){
		this.position = [];
		this.position.push(Math.floor(Math.random() * 100));
		this.position.push(Math.floor(Math.random() * 100));
	}

	//postURL
	public get _pos(): number[] {
		return this.position;
	}

	public set _pos(position: number[]) {
		this.position = position;
	}

	public getJSON() {
		return JSON.parse(JSON.stringify(this));
	}
}