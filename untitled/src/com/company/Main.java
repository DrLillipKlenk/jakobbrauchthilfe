package com.company;

import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Pfad zum Ordner eigeben");
        String folderPath = s.next();

        ArrayList<String> fileList = new ArrayList<>();

        File folder =new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileList.add(file.getName());
            }
        }

        List<String[]> contents = new ArrayList<>();
        contents.add(new String[]{"Gruppenname","Stufe","Vorname","Nachname","Geschlecht"});

        for(int i=0;i<fileList.size();i++){
            List <String> input = Files.readAllLines(Paths.get(folderPath+"\\"+fileList.get(i)));

            String klasse = input.get(0).replace(",","").split(" ")[1];

            for(int a=2;a<input.size();a++){
                String[] curr = input.get(a).split(",");
                contents.add(new String[]{"GMO_"+klasse,klasse.split("")[0],curr[2],curr[1],curr[6]});
            }
        }

        File csvFile = new File(folderPath+"\\JWINF_Import.csv");
        FileWriter fileWriter = new FileWriter(csvFile);

        for (String[] data : contents) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                line.append("\"");
                line.append(data[i].replaceAll("\"","\"\""));
                line.append("\"");
                if (i != data.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();

        System.out.println("Datei wurde in Inputordner abgelegt");
    }
}