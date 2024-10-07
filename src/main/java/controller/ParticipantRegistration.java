package controller;

import repository.IRepository;
import repository.Repository;
import service.IService;

import java.util.Scanner;

public class ParticipantRegistration implements IParticipantRegistration {
    private Scanner scanner;
    private IService service;
    private IRepository repository;
     public boolean isParticipantRegistered = false;  // Флаг для отслеживания регистрации

    public ParticipantRegistration(Scanner scanner, IService service, IRepository repository) {
        this.scanner = scanner;
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void registrationOfParticipant() {
        // Пример взаимодействия с пользователем

            // Регистрация участника
            System.out.println("Введите ID участника регистрации (или 'exit' для выхода):");
            String idInput = scanner.nextLine();
            if (idInput.equalsIgnoreCase("exit")) {
                System.out.println("Зарегистрированные участники: " + ((Repository) repository).getParticipants());
                System.out.println("Результаты выборов: " + ((Repository) repository).getResults());
//                break;// Выход из программы
            }
            int id =0;
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
//                System.out.println("Ошибка: ID должен быть числом. Пожалуйста, попробуйте еще раз.");
//                continue; // Повторяем ввод
            }
            System.out.println("Введите имя и фамилию участника:");
            String name = scanner.nextLine(); // Считываем имя и фамилию как одну строку
            // Проверка на существующего участника
            if (service.isParticipantRegistered(id)) {
                System.out.println("Участник с таким ID уже зарегистрирован.");
//                continue; // Возвращаемся к регистрации
            }
            // Регистрация нового участника
            if (service.registrationParticipant(id, name)) {
                isParticipantRegistered = true;  // Успешная регистрация
                System.out.println("Участник зарегистрирован!");
            } else {
                System.out.println("Ошибка регистрации участника. Пожалуйста, попробуйте снова.");
                isParticipantRegistered = false;  // Невозможность голосовать
//                continue; // Возвращаемся к регистрации
            }
        }
    }


