class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

public class SILab2 {

    public static boolean function (User user, List<String> allUsers) { //1
        if (user!=null) { //2
            if (user.getUsername()!=null && user.getEmail()!=null && !allUsers.contains(user.getUsername())) { //3
                boolean atChar = false, dotChar = false; //4
                for (int i=0;i<user.getEmail().length();i++) { //5
                    if (user.getEmail().charAt(i)=='@') //6
                        atChar = true; //7
                    if (atChar && user.getEmail().charAt(i)=='.') { //8
                        dotChar = true; //9
                    } //10
                } //11
                if (atChar && dotChar) { //12
                    return true; //13
                } //14
            } //15
        } //16
        return false; //17
    } //18
}

