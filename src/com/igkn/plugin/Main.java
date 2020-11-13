package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main extends JavaPlugin {

    static String fileLanguage="src/documents/languagesList.txt";

    public static void main(String args[]) throws IOException {
        HashMap<String,String> languages=readFile();
    }

    @Override
    public void onEnable(){
        translateCommand();
    }

    @Override
    public void onDisable(){

    }

    public void translateCommand(){
       this.getCommand("tr").setExecutor(
                new TranslateComand(this));
    }

    public static HashMap<String,String> readFile(){
        HashMap<String,String> languages=new HashMap<String,String>();

        try {
            File myObj = new File(fileLanguage);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] parts = data.split(" ");
                String languageName = parts[0];
                String languageId = parts[1];

                languages.put(languageName,languageId);

                System.out.println("language: "+languageName+" id: "+languageId);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in file lecture.");
            e.printStackTrace();
        }

        return languages;
    }
}
