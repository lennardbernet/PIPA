package persistence;

public class Exercise {
    private int exerciseID;
    private String name;
    private int workoutIDKFK;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkoutIDKFK() {
        return workoutIDKFK;
    }

    public void setWorkoutIDKFK(int workoutIDKFK) {
        this.workoutIDKFK = workoutIDKFK;
    }
}
