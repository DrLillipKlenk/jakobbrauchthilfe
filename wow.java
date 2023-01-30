package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<String> fileList = new ArrayList<>();

        File folder =new File("H:\\testFolder");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileList.add(file.getName());
            }
        }

        for(int i=0;i<fileList.size();i++){
            List <String> input = Files.readAllLines(Paths.get("H:\\testFolder\\"+fileList.get(i)));

            String klasse = input.get(0).replace(",","").split(" ")[1];

            for(int a=2;a<input.size();a++){
                String[] curr = input.get(a).split(",");
                Arrays.stream(curr).forEach(n->System.out.println(n));
            }
        }
    }
}
