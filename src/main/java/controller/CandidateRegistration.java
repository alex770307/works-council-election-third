package controller;

import entity.Candidate;
import repository.IRepository;
import repository.Repository;
import service.IService;

import java.util.Scanner;

public class CandidateRegistration implements ICandidateRegistration{
    private Scanner scanner;
    private IService service;
    private IRepository repository;

    public CandidateRegistration(Scanner scanner, IService service, IRepository repository) {
        this.scanner = scanner;
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void addCandidate() {
        while (true) {

            System.out.println("Введите номер кандидата, его имя и фамилию (можно с пробелами)(или 'exit' для выхода): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Кандидаты :  " + ((Repository) repository).getCandidates());
                break;
            }
            String[] parts = input.split(" ", 2); // Разделяем на номер и остальную часть строки

            // Проверка на корректность ввода
            if (parts.length < 2) {
                System.out.println("Ошибка ввода данных. Убедитесь, что введены номер и полное имя кандидата.");
                continue;
            }
            try {
                int candidateNumber = Integer.parseInt(parts[0]);
                String fullName = parts[1]; // Всё, что после номера, считается полным именем кандидата

                // Создаем кандидата
                Candidate candidate = new Candidate(candidateNumber, fullName);

                // Добавляем кандидата в репозиторий
                repository.addCandidate(candidate);
                System.out.println("Кандидат добавлен успешно.");
                System.out.println("   " + ((Repository) repository).getCandidates());

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Номер кандидата должен быть числом.");
            }
        }
    }
}
