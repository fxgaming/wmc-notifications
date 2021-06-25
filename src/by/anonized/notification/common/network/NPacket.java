package by.anonized.notification.common.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

public abstract class NPacket {
	public static final String a = "notification";
	private static final BiMap<Integer, Class> b;
	public static int c = 0;

	public static NPacket a(int a) throws IllegalAccessException, InstantiationException {
		Class c = b.get(a);
		if (c != null) {
			return (NPacket)c.newInstance();
		} else {
			return null;
		}
	}

	public final int b() {
		if (b.inverse().containsKey(this.getClass())) {
			return b.inverse().get(this.getClass());
		} else {
			throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " is missing a mapping!");
		}
	}

	public final Packet c() {
		ByteArrayDataOutput a = ByteStreams.newDataOutput();
		a.writeByte(this.b());
		this.writeData(a);
		return PacketDispatcher.getPacket(this.a, a.toByteArray());
	}

	public abstract void writeData(ByteArrayDataOutput a);
	public abstract void readData(ByteArrayDataInput a);
	public abstract void onPacket(EntityPlayer a, Side b);

	static {
		Builder builder = ImmutableBiMap.builder();
		builder.put(d(), PacketNotificationToClient.class);
		b = builder.build();
	}
	
	public static int d() {
		return c++;
	}
}
