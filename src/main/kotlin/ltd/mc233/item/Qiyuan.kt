package ltd.mc233.item

import catserver.api.bukkit.ForgeEventV2
import com.pixelmonmod.pixelmon.api.events.battles.BattleStartedEvent
import com.pixelmonmod.pixelmon.api.events.battles.TurnEndEvent
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored
import taboolib.platform.util.buildItem
import taboolib.platform.util.giveItem
import taboolib.platform.util.hasName


object Qiyuan {
    private val item = buildItem(Material.valueOf("PIXELMON_EVER_STONE")) {
        name = "§d起源之石"
        lore.add("&6 不推荐对战时使用".colored())
        lore.add("&7---------------------------".colored())
        lore.add("§7§o宝可梦携带对战时 所有属性+6")
        lore.add("&7---------------------------".colored())
        enchants.put(Enchantment.ARROW_DAMAGE, 1)
        hideAll()
    }

    fun GetItem(player: Player) {
        player.giveItem(item)
    }

    @SubscribeEvent
    fun shoufa(evnet: ForgeEventV2) {
        val ForgeEvent = evnet.forgeEvent
        if (ForgeEvent is BattleStartedEvent) {
            val event = ForgeEvent
            val bc = event.battleController
            bc.activePokemon.forEach { pokemon ->
                val item = CraftItemStack.asBukkitCopy(pokemon.pokemon.heldItem)
                if (item.hasName("§d起源之石")) {
                    pokemon.battleStats.modifyStat(6, BattleStatsType.ATTACK)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.DEFENSE)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.SPECIAL_ATTACK)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.SPECIAL_DEFENSE)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.SPEED)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.ACCURACY)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.EVASION)
                }
            }
        }
    }

    @SubscribeEvent
    fun huihe(evnet: ForgeEventV2) {
        val ForgeEvent = evnet.forgeEvent
        if (ForgeEvent is TurnEndEvent) {
            val event = ForgeEvent
            val bc = event.battleController
            bc.activePokemon.forEach { pokemon ->
                val item = CraftItemStack.asBukkitCopy(pokemon.pokemon.heldItem)
                if (item.hasName("§d起源之石")) {
                    pokemon.battleStats.modifyStat(6, BattleStatsType.ATTACK)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.DEFENSE)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.SPECIAL_ATTACK)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.SPECIAL_DEFENSE)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.SPEED)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.ACCURACY)
                    pokemon.battleStats.modifyStat(6, BattleStatsType.EVASION)
                }
            }
        }
    }


}