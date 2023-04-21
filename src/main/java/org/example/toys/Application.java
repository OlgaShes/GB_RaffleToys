package org.example.toys;

import java.util.ArrayList;
import java.util.Random;

public class Application {
    protected static String csvPath = "toys.csv";
    protected static String prizesPath = "prizes.txt";
    private Toy toy = new Toy();
    private ToyList toyList = new ToyList();
    private WinToy winList = new WinToy();

    protected void showAvailableToys() {
        toyList.readFromCSV(csvPath);
        toyList.print();
    }

    protected void addToyToList(String name, int quantity, int chance) {
        toyList.readFromCSV(csvPath);
        toy.setToyName(name);
        toy.setChance(chance);
        toy.setQuantity(quantity);
        toyList.add(toy);
        toyList.writeToCSV(csvPath);
        System.out.println("Игрушка добавлена");
    }

    protected void addPrize(String ticketNumber, String name) {
        winList.readFromTXT(prizesPath);
        winList.add(ticketNumber, name);
        winList.writeToTxt(prizesPath);
    }

    protected Toy getToyForChange(int index) {
        toyList.readFromCSV(csvPath);
        try {
            System.out.println("Найдена игрушка: " + toyList.get(index).toString());
            return toyList.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс превышает количество игрушек в списке");
            return null;
        }
    }

    protected void deleteToyFromList(int index) {
        toyList.readFromCSV(csvPath);
        toyList.delete(index);
        toyList.writeToCSV(csvPath);
        System.out.println("Игрушка удалена");
    }

    protected void changeToyChance(int index, int newChance) {
        toyList.readFromCSV(csvPath);
        toy = toyList.get(index);
        if (toyList.sumOfChances() - toy.getChance() + newChance <= 100) {
            toy.setChance(newChance);
            toyList.writeToCSV(csvPath);
            System.out.println("Изменения сохранены");
        } else {
            System.out.println("Суммарная вероятность по всем игрушками не может превышать 100%");
            System.out.println("Изменения не сохранены");
        }
    }

    protected void changeToyQuantity(int index, int newQuantity) {
        toyList.readFromCSV(csvPath);
        toy = toyList.get(index);
        toy.setQuantity(newQuantity);
        toyList.writeToCSV(csvPath);
        System.out.println("Изменения сохранены");
    }

    protected void changeToyName(int index, String newName) {
        toyList.readFromCSV(csvPath);
        toy = toyList.get(index);
        toy.setToyName(newName);
        toyList.writeToCSV(csvPath);
        System.out.println("Изменения сохранены");
    }

    protected void raffleToys(String ticketNumber) {
        winList.readFromTXT(prizesPath);
        if (winList.containsTicket(ticketNumber)) System.out.println("Данный билет уже ожидает выдачи приза");
        else {
            ArrayList<Integer> raffleList = new ArrayList<>();
            toyList.readFromCSV(csvPath);
            ArrayList<Integer> chanses = toyList.listOfChanses();
            for (int i = 0; i < chanses.size(); i++) {
                for (int j = 0; j < chanses.get(i); j++) {
                    raffleList.add(i);
                }
            }
            while (raffleList.size() <= 100) {
                raffleList.add(-1);
            }
            int indexWin = raffleList.get(new Random().nextInt(101));
            if (indexWin == -1) System.out.println("Билет " + ticketNumber + " не выиграл");
            else {
                Toy currentToy = toyList.get(indexWin);
                System.out.println("Билет " + ticketNumber + ", выигрыш: " + currentToy.getToyName());
//            записать в файл выигрыша
                addPrize(ticketNumber, currentToy.getToyName());
                if (currentToy.getQuantity() > 1)
                    currentToy.setQuantity(currentToy.getQuantity() - 1);
                else toyList.delete(indexWin);
                toyList.writeToCSV(csvPath);
            }
        }
    }

    protected void showPrizes() {
        winList.readFromTXT(prizesPath);
        System.out.println(winList);
    }

    protected void giveUpPrize(String ticketNumber) {
        winList.readFromTXT(prizesPath);
        if (winList.containsTicket(ticketNumber))
            System.out.println("По билету " + ticketNumber + " выдан приз: " + winList.remove(ticketNumber));
        else System.out.println("По данному билету приза к выдаче нет");
        winList.writeToTxt(prizesPath);
    }

}
