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
        if(args[0].equals(help)){
            LlamadaHelp();
        }else {
            String text= "";
            for (int i = 1; i < args.length; i++)
                text += args[i];
            TranslateCall(args[0], text);
            Player p = (Player) sender;
            p.sendMessage(ChatColor.YELLOW+text);
        }
        return true;
    }
    //Method of translation command
    public void TranslateCall(String languaje, String text){
        //Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Commands are only available on Minecraft, not on a cmd");
        //TextToTranslate(args, text);

        try {
            Bukkit.getConsoleSender().sendMessage(GoogleTranslate.translate(languaje, text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Use of help command
    public void LlamadaHelp(){
        String languages=Languages.values().toString();
        Bukkit.getConsoleSender().sendMessage("Languages: "+languages);
    }


}
