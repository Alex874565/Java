package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ManageDB {
    default Connection connect(String[] creds){
        try(Connection conn = DriverManager.getConnection(creds[0], creds[1], creds[2])){
            return DriverManager.getConnection(creds[0], creds[1], creds[2]);
        } catch (SQLException e) {
            throw new IllegalStateException("connect error", e);
        }
    }

    default boolean emailExists(String creds[], String email){
        try(Connection conn = connect(creds)){
            PreparedStatement query = conn.prepareStatement("SELECT COUNT(*) FROM GymWorkoutPlanner.users WHERE email = ?");
            query.setString(1, email);
            ResultSet rs = query.executeQuery();
                rs.next();
                if(rs.getInt(1) == 0){
                    return false;
                }else{
                    return true;
                }
        }catch(SQLException e){
            throw new IllegalStateException("emailExists error", e);
        }
    }

    default boolean userExists(String[] creds, String email, String pass){
        try(Connection conn = connect(creds)){
            PreparedStatement query = conn.prepareStatement("SELECT COUNT(*) FROM GymWorkoutPlanner.users WHERE email = ? AND password = ?");
            query.setString(1, email);
            query.setString(2, pass);
            ResultSet rs = query.executeQuery();
                rs.next();
                if(rs.getInt(1) == 0){
                    return false;
                }else{
                    return true;
                }
        }catch(SQLException e){
            throw new IllegalStateException("userExists error", e);
        }
    }

    default String getName(String[] creds, String email){
        try(Connection conn = connect(creds)){
            PreparedStatement query = conn.prepareStatement("SELECT name FROM GymWorkoutPlanner.users WHERE email = ?");
            query.setString(1, email);
            ResultSet rs = query.executeQuery();
            if(rs != null){
                rs.next();
                return rs.getString(1);
            }else{
                return "null";
            }
        }catch(SQLException e){
            throw new IllegalStateException("getName error", e);
        }
    }
}
