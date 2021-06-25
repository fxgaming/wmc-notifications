package by.anonized.notification.common.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class NotificationsHandler implements IPacketHandler {
	public void onPacketData(INetworkManager a, Packet250CustomPayload b, Player c) {
		try {
			EntityPlayer d = (EntityPlayer)c;
			ByteArrayDataInput e = ByteStreams.newDataInput(b.data);
			int f = e.readUnsignedByte();
			NPacket g = NPacket.a(f);
			g.readData(e);
			g.onPacket(d, d.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} catch (InstantiationException h) {
			throw new RuntimeException("Unexpected Reflection exception during Packet construction!", h);
		} catch (IllegalAccessException i) {
			throw new RuntimeException("Unexpected Reflection exception during Packet construction!", i);
		}
	}
}
