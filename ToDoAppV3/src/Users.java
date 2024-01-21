//This class is needed to store the user information and to be used in the login, register and main frame  
public class Users {
    private String username;
    private String password;
    private String name;

    public Users(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Users(){
        
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }
}
