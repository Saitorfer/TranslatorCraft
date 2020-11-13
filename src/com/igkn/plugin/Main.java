package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    public static void main(String args[]) throws IOException {

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

    public  HashMap<String,String> readFile(String nameFile){
        HashMap<String,String> languages=new HashMap<String,String>();


        return languages;
    }
}
