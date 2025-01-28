package ltd.mc233.event

import catserver.api.bukkit.ForgeEventV2
import com.pixelmonmod.pixelmon.api.events.CaptureEvent
import com.pixelmonmod.pixelmon.api.pokemon.item.pokeball.PokeBallRegistry
import com.pixelmonmod.pixelmon.entities.pokeballs.PokeBallEntity
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType
import taboolib.common.platform.event.SubscribeEvent



object Rate {
    private val capture = NamespacedKey.fromString("ub:capture")!!
        @SubscribeEvent
        fun rate(e: ForgeEventV2){
            val forgeEvent = e.forgeEvent
            if (forgeEvent is CaptureEvent.StartCapture) {
                val event = forgeEvent
                val player = Bukkit.getPlayer(event.player.func_110124_au())!!
                val data = player.persistentDataContainer
                when (data.get(capture, PersistentDataType.INTEGER)){
                    1 -> {
                        event.captureValues.isCaught = true
                    }
                    2 -> {
                        event.captureValues.isCaught = false
                        event.captureValues.catchRate = 0
                        event.captureValues.ballBonus = 0.0
                        event.captureValues.shakes = 0
                    }
                    3 -> {
                        event.isCanceled = true
                    }
                    else -> {return}
                }
            }
        }

}