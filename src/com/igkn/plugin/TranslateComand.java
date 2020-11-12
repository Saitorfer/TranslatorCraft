package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class TranslateComand implements CommandExecutor {
    Main translatorCraft;

    public TranslateComand(Main translatorCraft) {
        this.translatorCraft = translatorCraft;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)){
            //Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Commands are only available on Minecraft, not on a cmd");
            String textoTraducir = "";
            for (int i = 1; i < args.length; i++)
                textoTraducir += args[i];
            try {
                Bukkit.getConsoleSender().sendMessage(GoogleTranslate.translate(args[0]+"", textoTraducir));
            } catch (IOException e) {
                e.printStackTrace();
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
}
