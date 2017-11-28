package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

public class Profile {
    // Variable name(s):
    private String username;
    private String password;
    private String imageName;
    private String movieString;

    Profile(String newUsername, String newPassword, String newImageName, String newMovieString){
        username = newUsername;
        password = newPassword;
        imageName = newImageName;
        movieString = newMovieString;
    }

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

    public String getMovieString(){
        return movieString;
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

    public void setMovieString(String newMovieString){
        movieString = newMovieString;
    }
}
