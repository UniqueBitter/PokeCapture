package ltd.mc233.utils

import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType
import com.pixelmonmod.pixelmon.api.storage.StorageProxy
import org.bukkit.OfflinePlayer
import taboolib.platform.util.broadcast

object Full6v {
    fun run(player: OfflinePlayer?, slot: Int) {
        val pps = StorageProxy.getParty(player?.uniqueId)
        val pokemon = pps.get(slot - 1) ?: return
        val ivs = pokemon.stats.iVs
        ivs.setStat(BattleStatsType.HP, 31)
        ivs.setStat(BattleStatsType.ATTACK, 31)
        ivs.setStat(BattleStatsType.DEFENSE, 31)
        ivs.setStat(BattleStatsType.SPECIAL_ATTACK, 31)
        ivs.setStat(BattleStatsType.SPECIAL_DEFENSE, 31)
        ivs.setStat(BattleStatsType.SPEED, 31)
        pokemon.stats.recalculateStats()
        //pokemon.addFlag("unbreedable") // 设置为不可繁殖
    }
}