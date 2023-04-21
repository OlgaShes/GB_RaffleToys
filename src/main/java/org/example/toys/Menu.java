package org.example.toys;

import java.util.Scanner;

public class Menu {
    private static Scanner iScanner = new Scanner(System.in);
    private static Application ap = new Application();
    private static String separator = "====================";
    private static String mainMenu = "Вы находитесь в главном меню, используйте команды:\n" +
            "1 - Показать список игрушек к розыгрышу\n" +
            "2 - Показать список игрушек к выдаче\n" +
            "3 - Редактировать список игрушек\n" +
            "4 - Провести розыгрыш призов\n" +
            "5 - Выдать игрушку\n" +
            "0 - Закончить программу";

    private static String changeListMenu = "1 - Добавить игрушку\n" +
            "2 - Изменить данные по существующей игрушке\n" +
            "0 - Выйти из редактирования";

    private static String editMenu = "1 - Удалить игрушку\n" +
            "2 - Изменить вероятность выпадения игрушки\n" +
            "3 - Изменить количество игрушек\n" +
            "4 - Изменить название игрушки\n" +
            "0 - Выйти из редактирования";

    public static void startMenu() {
        String command = "";
        String ticket;
        while (!command.equals("0")) {
            System.out.println(separator);
            System.out.println(mainMenu);
            System.out.println(separator);
            System.out.print("--> ");
            command = iScanner.nextLine();
            System.out.println(separator);
            switch (command) {
                case "1":
                    ap.showAvailableToys();
                    break;
                case "2":
                    ap.showPrizes();
                    break;
                case "3":
                    // редактирование списка
                    changingMenu();
                    break;
                case "4":
                    System.out.println("Введите номер билета к розыгрышу " +
                            "(номер билета должен содержать 5 цифр)");
                    ticket = iScanner.nextLine();
                    if (ticketNumberTest(ticket))
                        ap.raffleToys(ticket);
                    break;
                case "5":
                    System.out.println("Введите номер билета для выдачи приза " +
                            "(номер билета должен содержать 5 цифр)");
                    ticket = iScanner.nextLine();
                    if (ticketNumberTest(ticket))
                        ap.giveUpPrize(ticket);
                    break;
                case "0":
                    System.out.println("Приложение закрывется, все данные сохранены");
                    break;
                default:
                    System.out.println("Неверно введена команда");
            }
        }
    }

    private static void changingMenu() {
        String command = "";
        while (!command.equals("0")) {
            System.out.println(separator);
            System.out.println(changeListMenu);
            System.out.println(separator);
            System.out.print("--> ");
            command = iScanner.nextLine();
            System.out.println(separator);
            switch (command) {
                case "1":
                    // добавление игрушки
                    System.out.print("Введите название новой игрушки\n--> ");
                    String name = iScanner.nextLine();
                    try {
                        System.out.print("Введите доступное количество данной игрушки\n--> ");
                        int quantity = Integer.parseInt(iScanner.nextLine());
                        System.out.print("Введите вероятность выпадения новой игрушки  в процентах из 100 " +
                                "(внимание, общая сумма вероятностей не должна превышать 100%)\n--> ");
                        int chance = Integer.parseInt(iScanner.nextLine());
                        ap.addToyToList(name, quantity, chance);
                    } catch (Exception e) {
                        System.out.println("Ошибка ввода, количество и вероятность могут содержать только цифры");
                        System.out.println("Изменения не сохранены");
                    }
                    break;
                case "2":
//                    редактирование списка игрушек
                    try {
                        System.out.print("Введите индекс игрушки\n--> ");
                        int index = Integer.parseInt(iScanner.nextLine());
                        Toy toy = ap.getToyForChange(index);
                        if (toy != null) {
                            System.out.println(separator);
                            System.out.println(editMenu);
                            System.out.println(separator);
                            System.out.print("--> ");
                            String answer = iScanner.nextLine();
                            switch (answer) {
                                case "1":
//                                1 - Удалить игрушку
                                    ap.deleteToyFromList(index);
                                    break;
                                case "2":
//                                2 - Изменить вероятность выпадения игрушки
                                    System.out.print("Введите новую вероятность выигрыша\n--> ");
                                    int chance = Integer.parseInt(iScanner.nextLine());
                                    ap.changeToyChance(index, chance);
                                    break;
                                case "3":
//                                3 - Изменить количество игрушек
                                    System.out.print("Введите новое количество игршек\n--> ");
                                    int quantity = Integer.parseInt(iScanner.nextLine());
                                    ap.changeToyQuantity(index, quantity);
                                    break;
                                case "4":
//                                4 - Изменить название игрушки
                                    System.out.print("Введите новое название игршки\n--> ");
                                    String newName = iScanner.nextLine();
                                    ap.changeToyName(index, newName);
                                    break;
                                case "0":
                                    System.out.println("Изменения не сохранены");
                                    break;
                                default:
                                    System.out.println("Неверно введена команда");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка ввода, необходмо вводить только цифры");
                        System.out.println("Изменения не сохранены");
                    }
                    break;
                case "0":
                    System.out.println("Возвращение в глвное меню");
                    break;
                default:
                    System.out.println("Неверно введена команда");
            }
        }
    }

    private static boolean ticketNumberTest(String ticket) {
        try {
            int number = Integer.parseInt(ticket);
        } catch (Exception e) {
            System.out.println("Номер билета должен содержать только цифры");
            return false;
        }
        if (ticket.length() == 5) return true;
        else {
            System.out.println("Номер билета должен содержать ровно 5 цифр");
            return false;
        }
    }
}
