package persistence;

import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DbConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_app", "root", "");
            statement = null;
            preparedStatement = null;
            resultSet = null;
        } catch (SQLException e) {
            System.out.println("connection failed");
            e.printStackTrace();
        }
    }

    public ArrayList<Workout> getWorkouts() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from workout");
            ArrayList<Workout> workouts = new ArrayList<Workout>();
            while (resultSet.next()) {
                workouts.add(new Workout(resultSet.getString(2)));
            }
            return workouts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Exercise> getExercises(String workoutName) {
        try {
            preparedStatement = connection.prepareStatement("select * from exercise where workoutIDFK in (select workoutID from workout where name = ?)");
            preparedStatement.setString(1, workoutName);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Exercise> exercises = new ArrayList<Exercise>();
            while (resultSet.next()) {
                exercises.add(new Exercise(resultSet.getString(2)));
            }
            return exercises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Detail> getDetails(String workoutName, String exerciseName) {
        try {
            preparedStatement = connection.prepareStatement("select * from detail where exerciseIDFK in (select exerciseID from exercise where name = ? and workoutIDFK in (select workoutID from workout where name = ?))");
            preparedStatement.setString(1, exerciseName);
            preparedStatement.setString(2, workoutName);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Detail> details = new ArrayList<Detail>();
            while (resultSet.next()) {
                details.add(new Detail(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5)));
            }
            return details;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertWorkout(String workoutName) {
        try {
            preparedStatement = connection.prepareStatement("INSERT  into workout (name) values (?)");
            preparedStatement.setString(1, workoutName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWorkout(String workoutName) {
        try {
            preparedStatement = connection.prepareStatement("delete from workout where name = ?");
            preparedStatement.setString(1, workoutName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertExercise(String workoutName, String exerciseName) {
        try {
            preparedStatement = connection.prepareStatement("select * from exercise where workoutIDFK in (select workoutID from workout where name = ?)");
            preparedStatement.setString(1, workoutName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int workoutIDFK = resultSet.getInt(3);

            preparedStatement = connection.prepareStatement("INSERT  into exercise (name,workoutIDFK) values (?,?)");
            preparedStatement.setString(1, exerciseName);
            preparedStatement.setInt(2, workoutIDFK);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExercise(String workoutName, String exerciseName) {
        try {
            preparedStatement = connection.prepareStatement("delete from exercise where name = ? and workoutIDFK in (select workoutID from workout where name = ?)");
            preparedStatement.setString(1, exerciseName);
            preparedStatement.setString(2, workoutName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetailTable(String workoutName, String exerciseName, ArrayList<Detail> details) {
        try {
            preparedStatement = connection.prepareStatement("delete from detail where exerciseIDFK in (select exerciseID from exercise where name = ? and workoutIDFK in (select workoutID from workout where name = ?))");
            preparedStatement.setString(1, exerciseName);
            preparedStatement.setString(2, workoutName);
            preparedStatement.executeUpdate();
            for (Detail detail : details) {
                preparedStatement = connection.prepareStatement("select exerciseID from exercise where name = ? ");
                preparedStatement.setString(1, exerciseName);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int exerciseID = resultSet.getInt(1);
                System.out.println(exerciseID);

                preparedStatement = connection.prepareStatement("INSERT into detail (sets,reps,weight,exerciseIDFK) values (?,?,?,?)");
                preparedStatement.setInt(1, detail.getSets());
                preparedStatement.setInt(2, detail.getReps());
                preparedStatement.setInt(3, detail.getWeight());
                preparedStatement.setInt(4, exerciseID);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
