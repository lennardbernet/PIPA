package rest;

import persistence.DbConnection;
import persistence.Detail;
import persistence.Exercise;
import persistence.Workout;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/se")
public class ServiceEndpoint {

    private DbConnection dbConnection;

    @GET
    @Path("/getWorkouts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkouts() {
        dbConnection = new DbConnection();
        return Response.ok(dbConnection.getWorkouts()).build();
    }

    @GET
    @Path("/getExercises")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExercises(
            @QueryParam("workout") String workoutName) {
        dbConnection = new DbConnection();
        return Response.ok(dbConnection.getExercises(workoutName)).build();
    }

    @GET
    @Path("/getDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetails(
            @QueryParam("workout") String workoutName,
            @QueryParam("exercise") String exerciseName) {
        dbConnection = new DbConnection();
        return Response.ok(dbConnection.getDetails(workoutName, exerciseName)).build();
    }

    @GET
    @Path("/insertWorkout")
    public void insertWorkout(
            @QueryParam("workout") String workoutName) {
        dbConnection = new DbConnection();
        dbConnection.insertWorkout(workoutName);
    }

    @GET
    @Path("/deleteWorkout")
    public void deleteWorkout(
            @QueryParam("workout") String workoutName) {
        dbConnection = new DbConnection();
        dbConnection.deleteWorkout(workoutName);
    }

    @GET
    @Path("/insertExercise")
    public void insertExercise(
            @QueryParam("exercise") String exerciseName) {
        dbConnection = new DbConnection();
        dbConnection.insertWorkout(exerciseName);
    }

    @GET
    @Path("/deleteExercise")
    public void deleteExercise(
            @QueryParam("workout") String workoutName,
            @QueryParam("exercise") String exerciseName) {
        dbConnection = new DbConnection();
        dbConnection.deleteExercise(workoutName, exerciseName);
    }

    @GET
    @Path("/updateDetailTable")
    public void updateDetailTable(
            @QueryParam("workout") String workoutName,
            @QueryParam("exercise") String exerciseName,
            @QueryParam("details") String details) {
        dbConnection = new DbConnection();
        ArrayList<Exercise> exercises = dbConnection.getExercises(workoutName);
        int exerciseID = 0;
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(exerciseName)) {
                exerciseID = exercise.getExerciseID();
            }
        }
        String[] parts = details.split("/");
        ArrayList<Detail> detailArrayList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(parts[0]); i++) {
            detailArrayList.add(new Detail(Integer.parseInt(parts[i + 1]), Integer.parseInt(parts[i + 2]), Integer.parseInt(parts[i + 3]), exerciseID));
        }
        dbConnection.updateDetailTable(workoutName, exerciseName, detailArrayList);
    }

}
