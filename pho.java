package org.example;

import java.util.*;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;

public class pho {

    private final Map<String, List<String>> book = new HashMap<>();

    public void addContact(String name, String phone) {
        List<String> phones = book.getOrDefault(name, new ArrayList<>());
        phones.add(phone);
        book.put(name, phones);
    }

    public void printContacts() {
        book.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    entry.getValue().forEach(phone -> System.out.println(" " + phone));
                });
    }

    public static void main(String[] args) {
        pho book = new pho();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить контакт");
            System.out.println("2. Напечатать контакты");
            System.out.println("3. Поиск по имени");
            System.out.println("4. Поиск по номеру");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Введите имя: ");
                String name = scanner.nextLine();
                System.out.print("Введите номер: ");
                String phone = scanner.nextLine();
                book.addContact(name, phone);
            } else if (choice == 2) {
                book.printContacts();
            } else if (choice == 3) {
                System.out.print("Введите имя для поиска: ");
                String name = scanner.nextLine();
                List<String> contacts = book.searchByName(name);
                if (contacts.isEmpty()) {
                    System.out.println("Контакт не найден");
                } else {
                    System.out.println(name);
                    contacts.forEach(phone -> System.out.println(" " + phone));
                }
            } else if (choice == 4) {
                System.out.print("Введите номер для поиска: ");
                String number = scanner.nextLine();
                List<String> contacts = book.searchByNumber(number);
                if (contacts.isEmpty()) {
                    System.out.println("Контакт не найден");
                }
            } else if (choice == 0) {
                break;
            }

        }

        scanner.close();
    }

    public List<String> searchByName(String name) {

        List<String> phones = book.getOrDefault(name, Collections.emptyList());

        phones.sort((p1, p2) -> p2.length() - p1.length());

        return phones;

    }

    public List<String> searchByNumber(String number) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : book.entrySet()) {
            if (entry.getValue().contains(number)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

}