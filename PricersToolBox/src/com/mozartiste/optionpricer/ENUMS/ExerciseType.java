package com.mozartiste.optionpricer.ENUMS;

public enum ExerciseType {
	EUROPEAN("European"), AMERICAN("American");

    private String exercise = "";

    ExerciseType(final String exercise) {
        this.exercise = exercise;
    }

    public String getExercise() {
        return exercise;
    }

    @Override
    public String toString() {
        return "ExerciseType{" +
                "exercise='" + exercise + '\'' +
                '}';
    }
}
