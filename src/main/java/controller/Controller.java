package controller;

import entity.Candidate;
import repository.IRepository;
import repository.Repository;
import service.IService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Controller implements IController {
    private Scanner scanner;
    private IService service;
    private IRepository repository;

    public Controller(Scanner scanner, IService service, IRepository repository) {
        this.scanner = scanner;
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void startInteraction() {
        while (true) {
            System.out.println("Введите ID участника регистрации (или 'exit' для выхода):");
            String idInput = scanner.nextLine();
            if (idInput.equalsIgnoreCase("exit")) {
                displayParticipantsAndResults(); // Выводим зарегистрированных участников и результаты
                break; // Выход из программы
            }
            int id = parseId(idInput);
            if (id < 0) continue; // Если ID некорректный, продолжаем

            System.out.println("Введите имя и фамилию участника:");
            String name = scanner.nextLine();

            if (service.isParticipantRegistered(id)) {
                System.out.println("Участник с таким ID уже зарегистрирован.");
                continue;
            }

            if (!service.registrationParticipant(id, name)) {
                System.out.println("Ошибка регистрации участника. Пожалуйста, попробуйте снова.");
                continue;
            }

            System.out.println("Участник зарегистрирован!");
            Set<Candidate> selectedCandidates = selectCandidates();
            if (!selectedCandidates.isEmpty()) {
                for (Candidate candidate : selectedCandidates) {
                    service.electionFromCandidates(candidate, 1);
                }
                System.out.println("Голос(а) за кандидатов " + selectedCandidates + " зарегистрирован(ы)!");
            }
        }
    }

    private int parseId(String idInput) {
        try {
            return Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом. Пожалуйста, попробуйте еще раз.");
            return -1; // Возвращаем некорректное значение
        }
    }

    private Set<Candidate> selectCandidates() {
        System.out.println("Введите имена кандидатов для голосования через пробел (максимум 2 кандидата):");
        String candidateInput = scanner.nextLine();
        String[] candidateNames = candidateInput.split(" ");

        if (candidateNames.length > 2) {
            System.out.println("Ошибка: Вы можете выбрать максимум 2 кандидата.");
            return new HashSet<>();
        }

        Set<Candidate> selectedCandidates = new HashSet<>();
        for (String candidateName : candidateNames) {
            try {
                int candidateNumber = Integer.parseInt(candidateName);
                Candidate candidate = repository.findCandidateById(candidateNumber);
                if (candidate != null) {
                    selectedCandidates.add(candidate);
                } else {
                    System.out.println("Кандидат с номером " + candidateNumber + " не найден.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Номер кандидата должен быть числом.");
            }
        }

        if (selectedCandidates.size() > 2) {
            System.out.println("Ошибка: Выберите максимум 2 уникальных кандидата.");
        } else if (selectedCandidates.isEmpty()) {
            System.out.println("Необходимо выбрать хотя бы одного кандидата для голосования.");
        }

        return selectedCandidates;
    }

    private void displayParticipantsAndResults() {
        System.out.println("Зарегистрированные участники: " + ((Repository) repository).getParticipants());
        System.out.println("Результаты выборов: " + ((Repository) repository).getResults());
    }
}