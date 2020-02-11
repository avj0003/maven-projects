package Models;

public class User {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String role;
    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";
    public static final String ADMIN = "admin";

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public User(String uname, String fn, String ln, String rl) {
        firstName = fn;
        lastName = ln;
        username = uname;
        role = rl;
    }
}
