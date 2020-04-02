package persistence;

public class Detail {
    private int detailID;
    private int sets;
    private int reps;
    private int weight;
    private int exerciseIDFK;

    public Detail() {
    }

    public Detail(int sets, int reps, int weight, int exerciseIDFK) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.exerciseIDFK = exerciseIDFK;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getExerciseIDFK() {
        return exerciseIDFK;
    }

    public void setExerciseIDFK(int exerciseIDFK) {
        this.exerciseIDFK = exerciseIDFK;
    }
}
