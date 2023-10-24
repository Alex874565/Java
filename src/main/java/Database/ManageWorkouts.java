package Database;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public interface ManageWorkouts {

    default Connection connect(String[] creds){
        try(Connection conn = DriverManager.getConnection(creds[0], creds[1], creds[2])){
            return DriverManager.getConnection(creds[0], creds[1], creds[2]);
        } catch (SQLException e) {
            throw new IllegalStateException("connect error", e);
        }
    }

    default void addWorkout(String[] creds, String email, String wname){
        try(Connection conn = connect(creds)){
            PreparedStatement query = conn.prepareStatement("INSERT INTO GymWorkoutPlanner.workouts (email, wname) VALUES (?, ?)");
            query.setString(1, email);
            query.setString(2, wname);
            query.executeUpdate();
        }catch(SQLException e){
            throw new IllegalStateException("addWorkout error", e);
        }
    }

    default void addDate(String[] creds, String email, String wname, String date){
        try(Connection conn = connect(creds)){
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime datetime = LocalDateTime.parse(date, f);
            Timestamp timestamp = Timestamp.valueOf(datetime);
            PreparedStatement query = conn.prepareStatement("UPDATE GymWorkoutPlanner.workouts SET date = ? WHERE email = ? AND wname = ?");
            query.setTimestamp(1, timestamp);
            query.executeUpdate();
        }catch(SQLException e){
            throw new IllegalStateException("addDate error", e);
        }
    }
}
