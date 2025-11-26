package org.example.repository;

import org.example.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {

    void add(Candidate candidate);

    boolean remove(Candidate candidate);

    List<Candidate> getAll();

    Optional<Candidate> findByName(String firstName, String lastName);

    void clear();
}
