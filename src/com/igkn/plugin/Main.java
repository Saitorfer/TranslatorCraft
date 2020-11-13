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

    public static void main(String args[]) throws IOException {
        //HashMap<String,String> languages=readFile();
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


}
