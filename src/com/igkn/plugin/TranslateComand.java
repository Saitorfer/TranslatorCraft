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
import java.util.HashMap;
import java.util.Map;

public class TranslateComand  implements CommandExecutor {
    Main translatorCraft;
    HashMap<String,String> listLanguages;
    String help="help";

    public TranslateComand(Main translatorCraft, HashMap<String,String> list) {
        this.translatorCraft = translatorCraft;
        this.listLanguages=list;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comand, String label, String[] args) {
        boolean languageExist;
        languageExist=LanguageExist(args[0]);

        if(args[0].equals(help)){
            LlamadaHelp(sender);
        }else {

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
        /*World world =  p.getWorld();*/

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendMessage(ChatColor.GREEN+senderPlayer.getName()+": "+GoogleTranslate.translate(languaje,text)+" (TRANSLATED)");
        }
    }

    //Use of help command
    public void LlamadaHelp(CommandSender sender){
        Player senderPlayer = (Player) sender;
        String textLanguages="";

        for (Map.Entry<String, String> entry : listLanguages.entrySet()) {
            textLanguages+=textLanguages+ ", "+entry.getValue()+" ("+entry.getKey()+") ";
        }
        senderPlayer.sendMessage(ChatColor.GOLD+"Languages: "+ChatColor.WHITE+textLanguages);

    }
    //Check that the language exists
    public boolean LanguageExist(String language){
        if(listLanguages.containsKey(language)) {
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
