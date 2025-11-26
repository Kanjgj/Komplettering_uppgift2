package org.example.tests;

import org.example.model.Candidate;
import org.example.repository.CandidateRepository;
import org.example.service.CandidateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateServiceSortTest {

    private CandidateService service;
    private CandidateRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CandidateRepository.class);
        service = new CandidateService(repository);
    }

    @Test
    void sortByFirstName() {
        List<Candidate> list = List.of(
                new Candidate("Zlatan", "I", 40, "Sport", 20),
                new Candidate("anna", "A", 30, "IT", 5),
                new Candidate("Björn", "B", 22, "IT", 1)
        );

        var sorted = service.sortByFirstName(list);

        assertEquals("anna", sorted.get(0).getFirstName(), "Första bör vara 'anna'");
        assertEquals("Björn", sorted.get(1).getFirstName(), "Andra bör vara 'Björn'");
        assertEquals("Zlatan", sorted.get(2).getFirstName(), "Tredje bör vara 'Zlatan'");
    }
}
