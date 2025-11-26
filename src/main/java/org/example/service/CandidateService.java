package org.example.service;

import org.example.filter.CandidateFilter;
import org.example.model.Candidate;
import org.example.repository.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateService.class);

    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void addCandidate(Candidate candidate) {
        logger.info("Service: Lägg till {}", candidate);
        repository.add(candidate);
    }

    public boolean removeCandidate(Candidate candidate) {
        logger.info("Service: Ta bort {}", candidate);
        return repository.remove(candidate);
    }

    public List<Candidate> getAllCandidates() {
        logger.info("Service: Hämtar alla kandidater.");
        return repository.getAll();
    }

    public List<Candidate> filterCandidates(List<CandidateFilter> filters) {
        logger.info("Service: Filtrerar kandidater med {} filter.",
                filters == null ? 0 : filters.size());

        var all = repository.getAll();

        if (filters == null || filters.isEmpty()) {
            return all;
        }

        return all.stream()
                .filter(candidate ->
                        filters.stream()
                                .allMatch(f -> f.toPredicate().test(candidate))
                )
                .collect(Collectors.toList());
    }

    public List<Candidate> sortByFirstName(List<Candidate> list) {
        logger.info("Service: Sorterar {} kandidater.", list == null ? 0 : list.size());

        if (list == null)
            return List.of();

        return list.stream()
                .sorted(Comparator.comparing(Candidate::getFirstName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }
}
