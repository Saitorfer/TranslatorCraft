package com.igkn.plugin;

import com.goxr3plus.speech.translator.GoogleTranslate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class TranslateComand  implements CommandExecutor {
    Main translatorCraft;
    String help="help";
    static String fileLanguage="src/documents/languagesList.txt";

    public TranslateComand(Main translatorCraft) {
        this.translatorCraft = translatorCraft;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comand, String label, String[] args) {
        boolean languageExist;
        HashMap<String,String> listLanguages=readFile();
        languageExist=LanguageExist(args[0],listLanguages);

        if(args[0].equals(help)){
            LlamadaHelp(sender,listLanguages);
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
    public void LlamadaHelp(CommandSender sender,HashMap<String,String> listLanguages){
        Player senderPlayer = (Player) sender;
        String textLanguages="";

        //for (Map.Entry<String, String> entry : listLanguages.entrySet()) {
        //    textLanguages+=textLanguages+ ", "+entry.getValue()+" ("+entry.getKey()+") ";
        //}
        for (String entry : listLanguages.values()) {
            textLanguages+=textLanguages+ ", "+entry;
        }
        senderPlayer.sendMessage(ChatColor.GOLD+"Languages: "+ChatColor.WHITE+textLanguages);

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
