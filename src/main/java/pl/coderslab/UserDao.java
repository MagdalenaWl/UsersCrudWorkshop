package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.User;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;




public class UserDao {
    private static final String URL = System.getenv("URL");
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");

    private static final String NEW_USER = "INSERT INTO users(username,email,password) VALUES (?,?,?)";
    private static final String GET_USER = "SELECT * FROM users WHERE id=?";
    private static final String UPDATE = "UPDATE users SET username=?, email=? WHERE id=?";
    public static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String GET_ALL_WHERE = "SELECT * FROM users WHERE ";

    public static Connection connect() throws SQLException {
        return DbUtil.getConnection();
    }

    public static User[] findAllWhere(String where) {
        User[] users = new User[0];
        try {
            Connection connection = connect();
            PreparedStatement preStmt = connection.prepareStatement(GET_ALL_WHERE + where);
            ResultSet rs = preStmt.executeQuery();
            while ((rs.next())) {
                User user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
                users = addToArray(users, user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } finally {
            return users;
        }


    }

    public static User[] findAll() {
        User[] users = new User[0];
        try {
            Connection connection = connect();
            PreparedStatement preStmt = connection.prepareStatement(GET_ALL);
            ResultSet rs = preStmt.executeQuery();
            while ((rs.next())) {
                User user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
                users = addToArray(users, user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } finally {
            return users;
        }


    }

    public static User[] addToArray(User[] users, User user) {
        User[] newUsers = Arrays.copyOf(users, users.length + 1);
        newUsers[newUsers.length - 1] = user;
        return newUsers;
    }

    public static void delete(long id) {
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, String.valueOf(id));
            if (preparedStatement.executeUpdate() != 0) {
                System.out.println("successful removal");
            } else {
                System.out.println("failed removal");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void update(String id, String username, String email) {
        try {
            Connection connection = connect();
            PreparedStatement preStmt = connection.prepareStatement(UPDATE);
            preStmt.setString(1, username);
            preStmt.setString(2, email);
            preStmt.setString(3, id);

            if (preStmt.executeUpdate() != 0) {
                System.out.println("successful update");
            } else {
                System.out.println("failed update");
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Email already exists");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public static User readUser(long id) {
        try {
            Connection connection = connect();
            PreparedStatement preStmt = connection.prepareStatement(GET_USER);
            preStmt.setString(1, String.valueOf(id));
            ResultSet rs = preStmt.executeQuery();
            while ((rs.next())) {
                User user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"));

                return user;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static User createUser(User user) {
        try {
            Connection connection = connect();
            PreparedStatement preStmt = connection.prepareStatement(NEW_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getUserName());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {

                long id = rs.getLong(1);
                User user1 = new User(id, user.getUserName(), user.getEmail(), user.getPassword());
                return user1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    public static boolean checkEmail(long id, String password){
        boolean check=false;
        User user=readUser(id);
        check=BCrypt.checkpw(password,user.getPassword());
        System.out.println(check);

        return check;
    }
}


