package by.anonized.notification.common.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import by.anonized.notification.client.ClientHandler;
import by.anonized.notification.common.Notification;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class PacketNotificationToClient extends NPacket {
	public Notification a;
	public PacketNotificationToClient(Notification b) {
		this.a = b;
	}
	
	public PacketNotificationToClient() {
	}

	public void writeData(ByteArrayDataOutput w) {
		w.writeUTF(this.a.a);
		w.writeUTF(this.a.b);
		w.writeUTF(this.a.c);
		w.writeInt(this.a.d);
		for (int i = 0; i != 12; i++) {
			w.writeInt(this.a.e[i]);
		}
		w.writeBoolean(this.a.f);
		w.writeBoolean(this.a.g);
		w.writeUTF(this.a.h);
	}

	public void readData(ByteArrayDataInput r) {
		String a = r.readUTF();
		String b = r.readUTF();
		String c = r.readUTF();
		int d = r.readInt();
		int[] e = new int[12];
		for (int i = 0; i != 12; i++) {
			e[i] = r.readInt();
		}
		boolean f = r.readBoolean();
		boolean g = r.readBoolean();
		String h = r.readUTF();
		Notification n = new Notification(a, b, c, d, e, f, g, h);
		this.a = n;
	}

	public void onPacket(EntityPlayer a, Side b) {
		if (b.isClient()) {
			ClientHandler.g.add(this.a);
		}
	}
}
