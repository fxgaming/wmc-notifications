package by.anonized.notification.common.commands;

import by.anonized.notification.common.Notification;
import by.anonized.notification.common.network.PacketNotificationToClient;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandSend extends CommandBase {
	public String getCommandName() {
		return "notification";
	}

	public String getCommandUsage(ICommandSender icommandsender) {
		return "notifications.notification";
	}

	public void processCommand(ICommandSender a, String[] b) {
		if (b.length == 0) {
			if (a instanceof EntityPlayer) ((EntityPlayer)a).addChatMessage("§7/notification <all/Player> <notification>");
			else System.out.println("/notification <all/Player> <notification>");
		} else {
			try {
				if (b.length == 2) {
					if (Notification.a(b[1])) {
						if (b[0].toLowerCase().equals("all")) {
							PacketDispatcher.sendPacketToAllPlayers(new PacketNotificationToClient(Notification.b(b[1])).c());
							if (a instanceof EntityPlayer) ((EntityPlayer)a).addChatMessage("§2Отправлено.");
							else System.out.println("Отправлено.");
						} else {
							String[] pla = MinecraftServer.getServer().getAllUsernames();
							for (String play : pla) {
								if (play.toLowerCase().equals(b[0].toLowerCase())) {
									if (a instanceof EntityPlayer) {
										PacketDispatcher.sendPacketToPlayer(new PacketNotificationToClient(Notification.b(b[1])).c(), (Player)((EntityPlayer)a).worldObj.getPlayerEntityByName(play));
										((EntityPlayer)a).addChatMessage("§2Отправлено.");
									} else {
										System.out.println("Отправить уведомление одному игроку можно только через аккаунт.");
									}
								}
							}
						}
					} else {
						if (a instanceof EntityPlayer) ((EntityPlayer)a).addChatMessage("§cСообщение не найдено " + b[1] + ".");
						else System.out.println("Сообщение не найдено " + b[1] + ".");
					}
				}
			} catch (Exception e) {
				if (a instanceof EntityPlayer) ((EntityPlayer)a).addChatMessage("§cПроизошла ошибка.");
				else System.out.println("Произошла ошибка.");
				e.printStackTrace();
				System.out.println("Произошла ошибка.");
			}
		}
	}
}

