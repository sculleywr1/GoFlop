export class IUser {
    userId!: number;
    username!: string;
    password!: string;
    userEmail!: string;
    level!: number;
    class1!: number;
    race!: number;
    profilePicURL!: string;
    firstName!: string;
    lastName!: string;
	gender!: string;
	dOB!: string;

    public get _userId(): number {
		return this.userId;
	}

	public set _userId(userId: number) {
		this.userId = userId;
	}

	public get _username(): string {
		return this.username;
	}

	public set _username(username: string) {
		this.username = username;
	}

	public get _password(): string {
		return this.password;
	}

	public set _password(password: string) {
		this.password = password;
	}

    public get _profilePicURL(){
        return this.profilePicURL;
    }
    
    public set _profilePicURL(profilePicture: string){
        this.profilePicURL = profilePicture;
    }

	public get _firstName(): string {
		return this.firstName;
	}

	public set _firstName(firstName: string) {
		this.firstName = firstName;
    }

	public get _lastName(): string {
		return this.lastName;
	}

	public set _lastName(lastName: string) {
		this.lastName = lastName;
	}

	public get _userEmail(): string {
		return this.userEmail;
	}

	public set _userEmail(userEmail: string) {
		this.userEmail = userEmail;
    }

	public get _level(): number {
		return this.level;
	}

	public set _level(level: number) {
		this.level = level;
	}

	public get _class1(): number {
		return this.class1;
	}

	public set _class1(class1: number) {
		this.class1 = class1;
	}

	public get _race(): number {
		return this.race;
	}

	public set _race(race: number) {
		this.race = race;
	}

	public get _gender(): string {
		return this.gender;
	}

	public set _gender(gender: string) {
		this.gender = gender;
	}

	public get _dOB(): string {
		return this.dOB;
	}

	public set _dOB(dOB: string) {
		this.dOB = dOB;
	}
}