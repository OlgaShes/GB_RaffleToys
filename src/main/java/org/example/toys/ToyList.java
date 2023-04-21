package org.example.toys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToyList {
    private ArrayList<Toy> toyList;

    protected ToyList() {
        this.toyList = new ArrayList<>();
    }

    protected void add(Toy toy) {
//        надо проверить по вероятностям!
        int totalChance = sumOfChances();
        if (totalChance + toy.getChance() <= 100) {
            toyList.add(toy);
        } else {
            System.out.println("Данная игрушка не может быть добавлена, исправьте вероятности " +
                    "(суммарная вероятность по всем игрушкам не может превышать 100%)");
        }
    }

    protected void delete(int index) {
        toyList.remove(index);
    }

    protected Toy get(int index) {
        return toyList.get(index);
    }

    protected void print() {
        for (int i = 0; i < toyList.size(); i++) {
            System.out.printf("id %d: %s", i, toyList.get(i));
            System.out.println();
        }

    }

    protected ArrayList<Integer> listOfChanses() {
        ArrayList<Integer> chanceList = new ArrayList<>();
        for (Toy t : toyList) {
            chanceList.add(t.getChance());
        }
        return chanceList;
    }

    protected int sumOfChances() {
        ArrayList<Integer> chanceList = listOfChanses();
        int result = 0;
        for (int cl : chanceList) {
            result += cl;
        }
        return result;
    }

    protected int size() {
        return toyList.size();
    }

    protected void writeToCSV(String path) {
        try (FileWriter fw = new FileWriter(path, false)) {
            for (int i = 0; i < toyList.size(); i++) {
                fw.write(toyList.get(i).getToyName() + ";" +
                        toyList.get(i).getQuantity() + ";" +
                        toyList.get(i).getChance() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
//            throw new RuntimeException(e);
        }
    }

    protected void readFromCSV(String path) {
        ArrayList<Toy> newList = new ArrayList<>();
        try {
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            String row;
            while ((row = br.readLine()) != null) {
                String[] data = row.split(";");
                Toy toy = new Toy();
                toy.setToyName(data[0]);
                toy.setQuantity(Integer.parseInt(data[1]));
                toy.setChance(Integer.parseInt(data[2]));
                newList.add(toy);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.toyList = newList;
//        return toyList;
    }
}
