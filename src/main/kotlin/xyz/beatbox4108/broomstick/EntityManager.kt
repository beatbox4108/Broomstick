package xyz.beatbox4108.broomstick

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Particle
import org.bukkit.entity.AreaEffectCloud
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.util.Transformation
import org.joml.Quaternionf
import org.joml.Vector3f
import java.util.*

object EntityManager {
    fun spawnAndRide(player: Player,namespacedKey: NamespacedKey): AreaEffectCloud{
        val broomstick: AreaEffectCloud=spawn(player.location.add(.0,.3,.0))
        broomstick.addPassenger(player)
        registerPlayer(broomstick,player,namespacedKey)
        return broomstick
    }
    fun registerPlayer(entity: Entity,player: Player,namespacedKey: NamespacedKey){
        entity.persistentDataContainer.set(namespacedKey, PersistentDataType.STRING,player.uniqueId.toString())
    }
    fun checkCanRide(player: Player): Boolean{
        if(player.fallDistance!=0f)return false
        return player.vehicle == null
    }
    private fun spawn(location: Location): AreaEffectCloud{
        location.yaw+=90f
        location.pitch=90f
        val broomstick: AreaEffectCloud=location.world.spawnEntity(location,EntityType.AREA_EFFECT_CLOUD) as AreaEffectCloud
        broomstick.radius= 0.5F
        broomstick.particle = Particle.ENCHANTMENT_TABLE
        broomstick.duration=40
        broomstick.scoreboardTags.add("xyz.beatbox4108.broomstick:seat")
        val blockDisplay :ItemDisplay=location.world.spawnEntity(location,EntityType.ITEM_DISPLAY) as ItemDisplay
        val appearance: ItemStack=ItemStack(Material.STICK)
        val itemMeta: ItemMeta = appearance.itemMeta
        itemMeta.setCustomModelData(1)
        appearance.itemMeta=itemMeta
        blockDisplay.itemStack=appearance
        blockDisplay.setRotation(location.yaw,90f)
        blockDisplay.transformation = Transformation(
            Vector3f(0f,-.5f,0f),
            Quaternionf(0.0,.0,0.0,1.0),
            Vector3f(1f,1f,1f),
            Quaternionf(.0,.0,0.0,1.0)
        )

        broomstick.addPassenger(blockDisplay)
        broomstick.world.spawnParticle(Particle.GLOW,broomstick.location,50,1.0,.1,.1,.1)
        return broomstick
    }
    fun createNewBroomstickItemstack(count: Int =1):ItemStack{
        val item: ItemStack=ItemStack(Material.STICK)
        item.amount=count
        val itemMeta: ItemMeta = item.itemMeta
        itemMeta.setCustomModelData(1)
        itemMeta.displayName(Component.text("魔法のほうき", NamedTextColor.YELLOW))
        item.itemMeta=itemMeta
        return item
    }
    fun killAreaEffectCloud(entity: Entity,namespacedKey: NamespacedKey){
        entity.passengers.forEach{
            if(it.type==EntityType.ITEM_DISPLAY){it.remove()}
        }
        entity.world.spawnParticle(Particle.DRAGON_BREATH,entity.location,50,.1,.1,.1,.1)
        val ownerUUID: String = entity.persistentDataContainer.get(namespacedKey, PersistentDataType.STRING)?:return
        val owner: Player = entity.server.getEntity(UUID.fromString(ownerUUID)) as? Player ?: return
        owner.inventory.addItem(createNewBroomstickItemstack())
        owner.world.spawnParticle(Particle.GLOW,owner.eyeLocation,10,1.0,.1,.1,.1)
    }
    fun keepAreaEffectCloud(entity: AreaEffectCloud){
        entity.ticksLived=1
    }
}