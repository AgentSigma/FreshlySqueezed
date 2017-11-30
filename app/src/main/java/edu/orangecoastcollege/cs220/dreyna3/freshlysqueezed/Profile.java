package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable{
    // Variable name(s):
    private int id;
    private String username;
    private String password;
    private String imageName;

    Profile(String newUsername, String newPassword){
        id=-1;
        username = newUsername;
        password = newPassword;
        imageName = "default_profile_image.png";
    }

    public Profile(int id, String username, String password, String imageName){
        this.id = id;
        this.username = username;
        this.password = password;
        this.imageName = imageName;
    }

    protected Profile(Parcel in) {
        id = in.readInt();
        username = in.readString();
        password = in.readString();
        imageName = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    // Getter(s):
    public String getUsername(){
        return username;
        // t
    }

    public String getPassword(){
        return password;
    }

    public String getImage(){
        return imageName;
    }


    // Setter(s):
    public void setUsername(String newUsername){
        username = newUsername;
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public void setImageName(String newImageName){
        imageName = newImageName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(imageName);
    }
}
