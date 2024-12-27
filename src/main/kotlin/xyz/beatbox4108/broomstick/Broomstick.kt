package xyz.beatbox4108.broomstick

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class Broomstick : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        val broomstickOwnerUUIDKey: NamespacedKey=NamespacedKey(this,"OwnerUUID")
        server.pluginManager.registerEvents(EventHandler(broomstickOwnerUUIDKey),this)
        BroomstickKeeper(server).runTaskTimer(this,0,20)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
