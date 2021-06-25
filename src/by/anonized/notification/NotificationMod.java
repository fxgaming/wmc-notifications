package by.anonized.notification;

import by.anonized.notification.client.ClientHandler;
import by.anonized.notification.common.commands.CommandSend;
import by.anonized.notification.common.network.NotificationsHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "notificationsmod", name = "Notifications Mod", version = "1.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = true, channels = {"notification"}, packetHandler = NotificationsHandler.class)
public class NotificationMod {
	public static NotificationMod a;
	private ClientHandler b;
	
	public NotificationMod() {
		a = this;
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(this.b = new ClientHandler());
			TickRegistry.registerTickHandler(this.b, Side.CLIENT);
		}
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandSend());
	}
}
