package xyz.beatbox4108.broomstick

import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.AreaEffectCloud
import org.bukkit.entity.EntityType
import org.bukkit.scheduler.BukkitRunnable

class BroomstickKeeper(var server: Server) : BukkitRunnable() {
    override fun run() {
        server.worlds.forEach{ world ->
            world.players.forEach { player ->
                val vehicle = player.vehicle as? AreaEffectCloud ?: return
                EntityManager.keepAreaEffectCloud(vehicle)
            }
        }
    }

}