package org.example.filter;

import org.example.model.Candidate;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;

public class BranchFilter implements CandidateFilter {

    private final String branch;

    public BranchFilter(String branch) {
        this.branch = Objects.requireNonNull(branch).trim().toLowerCase(Locale.ROOT);
    }

    @Override
    public Predicate<Candidate> toPredicate() {
        return c -> c.getBranch().toLowerCase(Locale.ROOT).equals(branch);
    }
}
