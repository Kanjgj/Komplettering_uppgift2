package org.example;

import org.example.filter.BranchFilter;
import org.example.filter.CandidateFilter;
import org.example.filter.YearsExperienceFilter;
import org.example.model.Candidate;
import org.example.repository.CandidateRepository;
import org.example.repository.InMemoryCandidateRepository;
import org.example.service.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final CandidateService service;
    private final Scanner scanner = new Scanner(System.in);

    public Main(CandidateService service) {
        this.service = service;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addCandidate();
                case "2" -> removeCandidate();
                case "3" -> showCandidates();
                case "4" -> filterCandidates();
                case "0" -> running = false;
                default -> System.out.println("Ogiltigt val.");
            }
        }

        System.out.println("Avslutar.");
    }

    private void printMenu() {
        System.out.println("\n--- Kandidatmeny ---");
        System.out.println("1) Lägg till kandidat");
        System.out.println("2) Ta bort kandidat");
        System.out.println("3) Visa alla");
        System.out.println("4) Filtrera");
        System.out.println("0) Avsluta");
        System.out.print("Val: ");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Endast siffror är tillåtna, försök igen.");
            }
        }
    }

    private Integer readOptionalInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) return null;

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Endast siffror är tillåtna. Filtrering ignoreras.");
            return null;
        }
    }

    private void addCandidate() {
        System.out.print("Förnamn: ");
        String fn = scanner.nextLine();

        System.out.print("Efternamn: ");
        String ln = scanner.nextLine();

        int age = readInt("Ålder: ");
        System.out.print("Bransch: ");
        String branch = scanner.nextLine();

        int years = readInt("År erfarenhet: ");

        service.addCandidate(new Candidate(fn, ln, age, branch, years));
        System.out.println("Kandidat tillagd.");
    }

    private void removeCandidate() {
        System.out.print("Förnamn: ");
        String fn = scanner.nextLine();

        System.out.print("Efternamn: ");
        String ln = scanner.nextLine();

        var opt = service.getAllCandidates().stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(fn)
                        && c.getLastName().equalsIgnoreCase(ln))
                .findFirst();

        if (opt.isPresent()) {
            service.removeCandidate(opt.get());
            System.out.println("Kandidat borttagen.");
        } else {
            System.out.println("Ingen kandidat hittad.");
        }
    }

    private void showCandidates() {
        var list = service.getAllCandidates();
        if (list.isEmpty()) {
            System.out.println("Inga kandidater.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void filterCandidates() {
        List<CandidateFilter> filters = new ArrayList<>();

        System.out.print("Filter bransch: ");
        String branch = scanner.nextLine();
        if (!branch.isBlank()) {
            filters.add(new BranchFilter(branch));
        }

        Integer years = readOptionalInt("Min år erfarenhet: ");
        if (years != null) {
            filters.add(new YearsExperienceFilter(years));
        }

        var filtered = service.filterCandidates(filters);
        var sorted = service.sortByFirstName(filtered);

        if (sorted.isEmpty()) {
            System.out.println("Inga kandidater matchar filtren.");
        } else {
            sorted.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        CandidateRepository repo = new InMemoryCandidateRepository();
        CandidateService service = new CandidateService(repo);
        new Main(service).start();
    }
}
