package ltd.mc233.command

import com.pixelmonmod.pixelmon.api.storage.StorageProxy
import ltd.mc233.item.Qiyuan
import ltd.mc233.utils.Full6v
import ltd.mc233.utils.Paodan
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.int
import taboolib.common.platform.command.subCommand
import taboolib.module.chat.colored
import taboolib.platform.compat.replacePlaceholder
import taboolib.platform.util.buildItem
import taboolib.platform.util.giveItem
import taboolib.platform.util.onlinePlayers

@CommandHeader("ub", permission = "ub.type")
object command {
    @CommandBody(permission = "dew.system.data")
    val data = subCommand {
        dynamic(comment = "player") {
            suggestion<ProxyCommandSender> { _, _ ->
                listOf("get", "set")
            }
            dynamic(comment = "player") {
                suggestion<ProxyCommandSender> { _, _ ->
                    onlinePlayers.map { it.name }.toList()
                }
                dynamic(comment = "data") {
                    suggestion<ProxyCommandSender> { _, _ ->
                        listOf(
                             "capture"  // 改为符合规范的格式
                        )
                    }
                    execute<ProxyCommandSender> { sender, context, argument ->
                        val player = Bukkit.getPlayerExact(context.args()[2]) ?: run {
                            sender.sendMessage("§c玩家不在线")
                            return@execute
                        }

                        if (context.args()[1] == "get") {
                            val data = player.persistentDataContainer.get(
                                NamespacedKey("ub", argument.lowercase()),  // 确保小写
                                PersistentDataType.INTEGER
                            ) ?: run {
                                sender.sendMessage("§c${player.name}的${argument}数据不存在")
                                return@execute
                            }
                            sender.sendMessage("§a${player.name}的${argument}值为$data")
                        } else {
                            sender.sendMessage("§c请输入要设置的数值")
                        }
                    }
                    dynamic(comment = "value") {
                        execute<ProxyCommandSender> { sender, context, argument ->
                            val player = Bukkit.getPlayerExact(context.args()[2]) ?: run {
                                sender.sendMessage("§c玩家不在线")
                                return@execute
                            }

                            val field = context.args()[3].lowercase()

                            when(field) {
                                "capture" -> {
                                    val value = try {
                                        argument.toInt()
                                    } catch (e: NumberFormatException) {
                                        sender.sendMessage("§c请输入一个有效的整数")
                                        return@execute
                                    }

                                    if (value < 0 || value > 3) {
                                        sender.sendMessage("§c必须在0-3之间")
                                        return@execute
                                    }

                                    player.persistentDataContainer.set(
                                        NamespacedKey("ub", field),
                                        PersistentDataType.INTEGER,
                                        value
                                    )
                                }
                                else -> {
                                    sender.sendMessage("§c未知的字段类型")
                                    return@execute
                                }
                            }
                            sender.sendMessage("§a${player.name}的${field}值已设置为${argument}")
                        }
                    }
                }
            }
        }
    }

    @CommandBody
    val give = subCommand {
            execute<CommandSender> { sender, context, argument ->
                val player = sender as Player
                Qiyuan.GetItem(player)
            }
    }

    @CommandBody
    val full6v = subCommand {
        // 参数 user
        dynamic("slot") {
            execute<CommandSender> { sender, context, argument ->
                val player = sender as Player
                val slot = context.int("slot")
                val pps = StorageProxy.getParty(player.uniqueId)
                Full6v.run(player,slot)
                player.sendMessage("&a已将 $slot 槽位宝可梦改为6v".colored())

            }
        }
    }

    @CommandBody
    val paodan = subCommand {
        // 参数 user
        dynamic("slot") {
            execute<CommandSender> { sender, context, argument ->
                val player = sender as Player
                val slot = context.int("slot")
                val pps = StorageProxy.getParty(player.uniqueId)
                val pokemon = pps.get(slot-1)?:return@execute
                if (!pokemon.isEgg){
                    player.sendMessage("&4此栏位不是宝可梦蛋".colored())
                    return@execute
                }
                Paodan.run(pokemon)
                player.sendMessage("&a已将 $slot 槽位宝可梦已孵化".colored())

            }
        }
    }

}