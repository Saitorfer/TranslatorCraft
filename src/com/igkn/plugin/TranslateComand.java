package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import com.goxr3plus.speech.recognizer.Languages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class TranslateComand implements CommandExecutor {
    Main translatorCraft;
    String help="help";

    public TranslateComand(Main translatorCraft) {
        this.translatorCraft = translatorCraft;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comand, String label, String[] args) {
        if(!(sender instanceof Player)){

            if(args[0].equals(help)){
                LlamadaHelp();
            }else {
                TranslateCall(args);
            }

            return false;
        }
        else{
            Bukkit.getConsoleSender().sendMessage(label);
            for (String arg : args){
                Bukkit.getConsoleSender().sendMessage(args);
            }
            return true;
        }
    }
    //Separate the text to be translated
    public static String TextToTranslate(String[] args,String text){
        for (int i = 1; i < args.length; i++)
            text += args[i];

        return text;
    }
    //Method of translation
    public static void TranslateCall(String[] args){
        //Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Commands are only available on Minecraft, not on a cmd");
        String text = "";
        TextToTranslate(args, text);

        try {
            Bukkit.getConsoleSender().sendMessage(GoogleTranslate.translate(args[0], text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Use of help
    public static void LlamadaHelp(){
        String languages=Languages.values().toString();
        Bukkit.getConsoleSender().sendMessage("Languages: "+languages);
    }


}
