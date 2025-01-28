package ltd.mc233

import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.platform.BukkitPlugin


object Main : Plugin() {

    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("&a听雨小插件启动成功")
        Bukkit.getConsoleSender().sendMessage("&b听雨小插件启动成功")
        Bukkit.getConsoleSender().sendMessage("&c听雨小插件启动成功")
        Bukkit.getConsoleSender().sendMessage("&d听雨小插件启动成功")
    }
    val plugin by lazy { BukkitPlugin.getInstance() }
}
