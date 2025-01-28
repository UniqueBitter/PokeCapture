package ltd.mc233.utils

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType
import com.pixelmonmod.pixelmon.api.storage.StorageProxy
import org.bukkit.OfflinePlayer

object Paodan {
    fun run(pokemon: Pokemon) {
        if (!pokemon.isEgg){
            return
        }
        pokemon.hatchEgg(true)
    }
}