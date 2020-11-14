package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main extends JavaPlugin {

    public static void main(String args[]) { }

    @Override
    public void onEnable(){
        translateCommand();
    }

    @Override
    public void onDisable(){

    }

    public void translateCommand(){

       this.getCommand("tr").setExecutor(
                new TranslateComand(this, fillLanguageHashMap()));
    }

    public static HashMap<String, String> fillLanguageHashMap(){
        HashMap<String, String> languages = new HashMap<>();

        languages.put("ARABIC","ar");
        languages.put("BASQUE","eu");
        languages.put("CATALAN","ca");
        languages.put("DUTCH","nl");
        languages.put("ENGLISH","en");
        languages.put("FINNISH","fi");
        languages.put("FRENCH","fr");
        languages.put("GALICIAN","gl");
        languages.put("GERMAN","de");
        languages.put("HEBREW","he");
        languages.put("HUNGARIAN","hu");
        languages.put("ICELANDIC","is");
        languages.put("ITALIAN","it");
        languages.put("INDONESIAN","id");
        languages.put("JAPANESE","ja");
        languages.put("KOREAN","ko");
        languages.put("LATIN","la");
        languages.put("CHINESE","zh");
        languages.put("MALAYSIAN","sn");
        languages.put("NORWEGIAN","no");
        languages.put("POLISH","pl");
        languages.put("PORTUGUESE","pt");
        languages.put("ROMANIAN","ro");
        languages.put("RUSSIAN","ru");
        languages.put("SERBIAN","sr");
        languages.put("SLOVAK","sk");
        languages.put("SPANISH","es");
        languages.put("SWEDISH","sv");
        languages.put("TURKISH","tr");
        languages.put("ZULU","zu");

        return  languages;
    }

}
