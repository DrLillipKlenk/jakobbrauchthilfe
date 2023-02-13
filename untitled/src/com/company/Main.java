package com.company;

import javax.swing.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Act act = new Act();
        act.gui();
    }
}

class Act{

    public void gui() throws IOException{
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File file = chooser.getCurrentDirectory();
        chooser.showSaveDialog(null);

        createFile(file.getAbsolutePath());
    }

    public void createFile(String path) throws IOException {
        ArrayList<String> fileList = new ArrayList<>();

        File folder =new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileList.add(file.getName());
            }
        }

        List<String[]> contents = new ArrayList<>();
        contents.add(new String[]{"Gruppenname","Stufe","Vorname","Nachname","Geschlecht"});

        for(int i=0;i<fileList.size();i++){
            List <String> input = Files.readAllLines(Paths.get(path+"\\"+fileList.get(i)));

            String klasse = input.get(0).replace(",","").split(" ")[1];

            for(int a=2;a<input.size();a++){
                String[] curr = input.get(a).split(",");
                contents.add(new String[]{"GMO_"+klasse,klasse.split("")[0],curr[2],curr[1],curr[6]});
            }
        }

        new File(path+"\\output").mkdirs();

        File csvFile = new File(path+"\\output\\JWINF_Import.csv");
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

        System.out.println("Datei wurde unter: "+path+"\\output\\JWINF_Import.csv abgelegt");
    }
}
