package org.example.tests;

import org.example.model.Candidate;
import org.example.repository.CandidateRepository;
import org.example.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CandidateServiceAddRemoveTest {

    @Mock
    private CandidateRepository repository;

    private CandidateService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CandidateService(repository);
    }

    @Test
    void addCandidate_callsRepositoryAdd() {
        Candidate c = new Candidate("Test", "User", 20, "IT", 2);

        service.addCandidate(c);

        ArgumentCaptor<Candidate> captor = ArgumentCaptor.forClass(Candidate.class);
        verify(repository, times(1)).add(captor.capture());

        Candidate added = captor.getValue();
        assertEquals("Test", added.getFirstName());
        assertEquals("User", added.getLastName());
        assertEquals("IT", added.getBranch());
        assertEquals(20, added.getAge());
        assertEquals(2, added.getYearsOfExperience());
    }

    @Test
    void removeCandidate_callsRepositoryRemove() {
        Candidate c = new Candidate("A", "B", 30, "HR", 4);

        when(repository.remove(c)).thenReturn(true);

        boolean result = service.removeCandidate(c);

        verify(repository, times(1)).remove(c);
        assertTrue(result);
    }
}
