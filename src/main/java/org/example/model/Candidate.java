package org.example.model;

import java.util.Objects;

public class Candidate {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String branch;
    private final int yearsOfExperience;

    public Candidate(String firstName, String lastName, int age, String branch, int yearsOfExperience) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.age = age;
        this.branch = Objects.requireNonNull(branch);
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getBranch() {
        return branch;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %d år, Bransch: %s, Erfarenhet: %d år",
                firstName, lastName, age, branch, yearsOfExperience);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;
        return firstName.equalsIgnoreCase(candidate.firstName)
                && lastName.equalsIgnoreCase(candidate.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }
}
