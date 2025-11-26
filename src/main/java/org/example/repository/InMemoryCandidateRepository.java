package org.example.repository;

import org.example.model.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCandidateRepository implements CandidateRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryCandidateRepository.class);

    private final List<Candidate> storage = new ArrayList<>();

    @Override
    public synchronized void add(Candidate candidate) {
        logger.info("Lägger till kandidat: {}", candidate);
        storage.add(candidate);
    }

    @Override
    public synchronized boolean remove(Candidate candidate) {
        logger.info("Tar bort kandidat: {}", candidate);
        return storage.remove(candidate);
    }

    @Override
    public synchronized List<Candidate> getAll() {
        logger.info("Hämtar alla kandidater: {} st", storage.size());
        return List.copyOf(storage);
    }

    @Override
    public synchronized Optional<Candidate> findByName(String firstName, String lastName) {
        String fn = firstName.trim();
        String ln = lastName.trim();

        return storage.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(fn)
                        && c.getLastName().equalsIgnoreCase(ln))
                .findFirst();
    }

    @Override
    public synchronized void clear() {
        logger.info("Rensar alla kandidater.");
        storage.clear();
    }
}
