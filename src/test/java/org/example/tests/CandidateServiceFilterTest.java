package org.example.tests;

import org.example.filter.BranchFilter;
import org.example.filter.YearsExperienceFilter;
import org.example.model.Candidate;
import org.example.repository.CandidateRepository;
import org.example.service.CandidateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CandidateServiceFilterTest {

    private CandidateService service;
    private CandidateRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CandidateRepository.class);
        service = new CandidateService(repository);
    }

    @Test
    void filterByBranch_andYears() {
        List<Candidate> candidates = List.of(
                new Candidate("Anna", "A", 30, "IT", 5),
                new Candidate("Bengt", "B", 40, "Ekonomi", 10),
                new Candidate("Carl", "C", 22, "IT Support", 1)
        );

        when(repository.getAll()).thenReturn(candidates);

        var filters = List.of(
                new BranchFilter("IT"),
                new YearsExperienceFilter(3)
        );

        var result = service.filterCandidates(filters);

        assertEquals(1, result.size());
        assertEquals("Anna", result.get(0).getFirstName());
    }
}
