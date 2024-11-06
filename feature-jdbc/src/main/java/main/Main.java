package main;

import model.Household;
import service.HouseholdService;
import service.MemberService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HouseholdService householdService = new HouseholdService();
        MemberService memberService = new MemberService();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Домашнє Управління ---");
            System.out.println("1. Створити Домогосподарство");
            System.out.println("2. Додати Члена до Домогосподарства");
            System.out.println("3. Видалити Домогосподарство");
            System.out.println("4. Переглянути Всі Домогосподарства");
            System.out.println("5. Вийти");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введіть ID домогосподарства: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введіть адресу домогосподарства: ");
                    String address = scanner.nextLine();
                    householdService.createHousehold(id, address);
                    break;

                case 2:
                    System.out.print("Введіть ID домогосподарства для додавання члена сім'ї: ");
                    int householdId = scanner.nextInt();
                    scanner.nextLine();
                    Household household = householdService.getHouseholdById(householdId);

                    if (household != null) {
                        System.out.print("Введіть ID члена сім'ї: ");
                        int memberId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введіть ім'я члена сім'ї: ");
                        String name = scanner.nextLine();
                        System.out.print("Введіть вік члена сім'ї: ");
                        int age = scanner.nextInt();

                        memberService.addMemberToHousehold(household, memberId, name, age);
                    } else {
                        System.out.println("Домогосподарство не знайдено.");
                    }
                    break;

                case 3:
                    System.out.print("Введіть ID домогосподарства для видалення: ");
                    int deleteId = scanner.nextInt();
                    householdService.deleteHousehold(deleteId);
                    break;

                case 4:
                    System.out.println("Всі Домогосподарства:");
                    householdService.getAllHouseholds().forEach(System.out::println);
                    break;

                case 5:
                    exit = true;
                    System.out.println("Вихід з програми...");
                    break;

                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        }
    }
}

