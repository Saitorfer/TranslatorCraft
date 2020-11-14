package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TranslateComand  implements CommandExecutor {
    Main translatorCraft;
    HashMap<String, String> languages;

    public TranslateComand(Main translatorCraft, HashMap<String, String> languages) {
        this.translatorCraft = translatorCraft;
        this.languages = languages;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command comand, String label, String[] args) {
        boolean languageExist;
        Player senderPlayer = (Player) sender;
        languageExist=LanguageExist(args[0],languages);

        //Check if the file work
        if(languages.isEmpty()) {
            senderPlayer.sendMessage(ChatColor.RED + "Error in TranslatorCraft file [languages] not loaded.");
        }

        if (args[0].equals("help")){
            LlamadaHelp(sender, languages);
        } else{
            if(languageExist){
                String text= "";
                for (int i = 1; i < args.length; i++)
                    text += args[i]+" ";
                try {
                    TranslateCall(args[0], text, sender);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                MessageError(sender);
            }
        }
        return true;
    }

    //Method of translation command
    public void TranslateCall(String languaje, String text, CommandSender sender) throws IOException {
        Player senderPlayer = (Player) sender;

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendMessage(ChatColor.GREEN+senderPlayer.getName()+": "+GoogleTranslate.translate(languaje,text)+" (TRANSLATED)");
        }
    }

    //Use of help command
    public void LlamadaHelp(CommandSender sender,HashMap<String,String> listLanguages){
        Player senderPlayer = (Player) sender;
        String textLanguages="";

        for (Map.Entry<String, String> entry : listLanguages.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            textLanguages = textLanguages + ChatColor.BLUE+key+" -> "+ChatColor.WHITE+value+", ";
        }

        //Bukkit.getConsoleSender().sendMessage(textLanguages);
        senderPlayer.sendMessage(ChatColor.GOLD+"Languages: \n"+textLanguages);

    }
    //Check that the language exists
    public boolean LanguageExist(String language,HashMap<String,String> listLanguages){
        if(listLanguages.containsValue(language)) {
            return true;
        }else {
            return false;
        }
    }

    //Error Message
    public void MessageError(CommandSender sender){
        Player senderPlayer = (Player) sender;
        senderPlayer.sendMessage(ChatColor.RED+"The language does not exist, please use [/tr help].");

    }
}
