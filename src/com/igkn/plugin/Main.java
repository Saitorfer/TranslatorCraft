package com.igkn.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

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


}
