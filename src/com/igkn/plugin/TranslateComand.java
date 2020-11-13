package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import com.goxr3plus.speech.recognizer.Languages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class TranslateComand  implements CommandExecutor {
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
                text += args[i]+" ";
            try {
                TranslateCall(args[0], text, sender);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    //Method of translation command
    public void TranslateCall(String languaje, String text, CommandSender sender) throws IOException {
        Player senderPlayer = (Player) sender;
        /*World world =  p.getWorld();*/

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendMessage(ChatColor.GREEN+senderPlayer.getName()+": "+GoogleTranslate.translate(languaje,text)+" (TRANSLATED)");
        }
    }

    //Use of help command
    public void LlamadaHelp(){
        String languages=Languages.values().toString();
        Bukkit.getConsoleSender().sendMessage("Languages: "+languages);
    }


}
