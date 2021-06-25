package by.anonized.notification.client;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;

public class DrawHelper {
	public static void a(int a, int b, int c, int d, String e, float f) {
		b(a - 1, b - 1, c + 2, d + 2, "0x000000", 0.2F);
		b(a, b, c, d, e, f);
	}

	public static void b(int a, int b, int c, int d, String e, float f) {
		Color g=Color.decode(e);
		float h=(float)g.getRed()/255.0F;
		float i=(float)g.getGreen()/255.0F;
		float j=(float)g.getBlue()/255.0F;
		c(a, b, a + c, b + d, h, i, j, f);
	}

	public static void c(int a, int b, int c, int d, float e, float f, float g, float h) {
		Tessellator i = new Tessellator();
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3553);
		GL11.glColor4f(e, f, g, h);
		i.startDrawingQuads();
		i.addVertex((double) a, (double) d, 0.0D);
		i.addVertex((double) c, (double) d, 0.0D);
		i.addVertex((double) c, (double) b, 0.0D);
		i.addVertex((double) a, (double) b, 0.0D);
		i.draw();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public static void d(String a, int b, int c, int d) {
		Minecraft e = Minecraft.getMinecraft();
		e.fontRenderer.drawString(a, b, c, d);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public static String e(int a, int b, int c) {
		return String.format("#%02x%02x%02x", a, b, b);
	}

	public static int f() {
		Minecraft a = Minecraft.getMinecraft();
		ScaledResolution b = new ScaledResolution(a.gameSettings, a.displayWidth, a.displayHeight);
		return b.getScaledWidth();
	}

	public static int g() {
		Minecraft a = Minecraft.getMinecraft();
		ScaledResolution b = new ScaledResolution(a.gameSettings, a.displayWidth, a.displayHeight);
		return b.getScaledHeight();
	}
}
