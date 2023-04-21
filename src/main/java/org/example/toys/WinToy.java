package org.example.toys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WinToy {
    HashMap<String, String> winList;

    protected WinToy() {
        this.winList = new HashMap<>();
    }

    protected void add(String ticketNumber, String name) {
        winList.put(ticketNumber, name);
    }

    protected String remove(String ticketNumber) {
        return winList.remove(ticketNumber);
    }

    protected void writeToTxt(String path) {
        try (FileWriter fw = new FileWriter(path, false)) {
            for (String item : winList.keySet()) {
                fw.write(item + ";" + winList.get(item) + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
//            throw new RuntimeException(e);
        }
    }

    protected void readFromTXT(String path) {
        HashMap<String, String> newList = new HashMap<>();
        try {
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            String row;
            while ((row = br.readLine()) != null) {
                String[] data = row.split(";");
                newList.put(data[0], data[1]);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.winList = newList;
    }

    protected boolean containsTicket(String ticketNumber) {
        return winList.containsKey(ticketNumber);
    }

    @Override
    public String toString() {
        String result = "";
        for (String item : winList.keySet()) {
            result += "#" + item + ": " + winList.get(item) + "; ";
        }
        return result;
    }
// написать метод проверки вхождения ключа

}
