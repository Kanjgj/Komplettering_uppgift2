package org.example.filter;

import org.example.model.Candidate;
import java.util.function.Predicate;

public class YearsExperienceFilter implements CandidateFilter {

    private final int minYears;

    public YearsExperienceFilter(int minYears) {
        if (minYears < 0) throw new IllegalArgumentException("År måste vara >= 0");
        this.minYears = minYears;
    }

    @Override
    public Predicate<Candidate> toPredicate() {
        return c -> c.getYearsOfExperience() >= minYears;
    }
}
