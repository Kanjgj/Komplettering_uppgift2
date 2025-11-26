package org.example.filter;

import org.example.model.Candidate;

import java.util.List;
import java.util.function.Predicate;

public interface CandidateFilter {

    Predicate<Candidate> toPredicate();

    default List<Candidate> filter(List<Candidate> input) {
        if (input == null) {
            return List.of();
        }
        return input.stream()
                .filter(toPredicate())
                .toList();
    }
}
