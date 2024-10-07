package controller;

import entity.Candidate;
import repository.IRepository;
import repository.Repository;
import service.IService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class VotingProcess implements IVotingProcess{
    private Scanner scanner;
    private IService service;
    private IRepository repository;
    //private boolean isParticipantRegistered = false;  // Флаг для отслеживания регистрации

    public VotingProcess(Scanner scanner, IService service, IRepository repository) {
        this.scanner = scanner;
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void electionFromCandidates() {

            System.out.println("Введите имена кандидатов для голосования через пробел (максимум 2 кандидата):");
            String candidateInput = scanner.nextLine();
            String[] candidateNames = candidateInput.split(" "); // Разделяем по пробелам

            // Проверка на ограничение по количеству кандидатов и уникальность
            if (candidateNames.length > 2) {
                System.out.println("Ошибка: Вы можете выбрать максимум 2 кандидата.");
                return; // Возвращаемся к процессу выбора
            }

            Set<Candidate> selectedCandidates = new HashSet<>(); // Используем HashSet для уникальности
            for (String candidateNumberStr : candidateNames) {
                try {
                    int candidateNumber = Integer.parseInt(candidateNumberStr); // Пробуем преобразовать в число
                    Candidate candidate = repository.findCandidateById(candidateNumber); // Получаем кандидата из репозитория
                    // Проверяем, существует ли кандидат
                    if (candidate != null) {
                        selectedCandidates.add(candidate);
                    } else {
                        System.out.println("Кандидат с номером " + candidateNumber + " не найден.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Номер кандидата должен быть числом.");
                }
            }


            // Проверка, выбраны ли два уникальных кандидата
            if (selectedCandidates.size() > 2) {
                System.out.println("Ошибка: Выберите максимум 2 уникальных кандидата.");
            } else if (!selectedCandidates.isEmpty()) {
                for (Candidate candidate : selectedCandidates) {
                    service.electionFromCandidates(candidate, 1);
                }
                System.out.println("Голос(а) за кандидатов " + selectedCandidates + " зарегистрирован(ы)!");
            } else {
                System.out.println("Необходимо выбрать хотя бы одного кандидата для голосования.");
            }

        // Отображение текущего состояния репозитория
        System.out.println("Зарегистрированные участники: " + ((Repository) repository).getParticipants());
        System.out.println("Результаты выборов: " + ((Repository) repository).getResults());
    }
}
