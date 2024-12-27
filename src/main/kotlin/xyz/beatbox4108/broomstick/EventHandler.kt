package xyz.beatbox4108.broomstick

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityRemoveEvent
import org.bukkit.event.player.PlayerInteractEvent

class EventHandler(val namespacedKey: NamespacedKey): Listener{
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent){
        if(event.action == Action.LEFT_CLICK_BLOCK)return
        if(event.action == Action.LEFT_CLICK_AIR)return
        if(event.item?.type==Material.STICK) if(event.item?.itemMeta?.hasCustomModelData() == true) if(event.item?.itemMeta?.customModelData==1){
            if(EntityManager.checkCanRide(event.player)){
                EntityManager.spawnAndRide(event.player, namespacedKey)
                event.item!!.amount-=1
            }else{
                event.player.sendMessage("乗り物から降り、地面についた状態で再度お試しください。")
            }
        }
    }
    @EventHandler
    fun onEntityRemove(event: EntityRemoveEvent){
        val vehicle: Entity=event.entity
        if(vehicle.type==EntityType.AREA_EFFECT_CLOUD) if("xyz.beatbox4108.broomstick:seat" in vehicle.scoreboardTags){
            EntityManager.killAreaEffectCloud(vehicle,namespacedKey)
        }
    }
}