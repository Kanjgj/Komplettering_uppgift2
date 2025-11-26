Jag använde en prompt till ChatGPT för att få hjälp med att skapa JUnit-tester med Mockito för min CandidateService-klass.

Exempel på prompt jag använde:
"Skapa JUnit 5-tester för CandidateService som använder Mockito. Testerna ska verifiera att addCandidate() anropar repository.add(), och att removeCandidate() anropar repository.remove(). Skapa även tester för filterCandidates() och sortByFirstName()."

Resultat:
ChatGPT förklarade hur man använder @Mock och MockitoAnnotations.openMocks() för att skapa mock-objekt, samt hur man använder verify() och ArgumentCaptor för att kontrollera vilka värden som skickas in i repository-metoderna. Modellen visade också hur man testar filtrering och sortering med hjälp av listor och asserts.
Jag använde sedan det svaret som grund för mina testklasser:

CandidateServiceAddRemoveTest.java

CandidateServiceFilterTest.java

CandidateServiceSortTest.java
