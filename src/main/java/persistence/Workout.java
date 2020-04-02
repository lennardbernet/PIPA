package persistence;

public class Workout {
    private int workoutID;
    private String name;

    public Workout() {
    }

    public Workout(String name) {
        this.name = name;
    }

    public int getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
