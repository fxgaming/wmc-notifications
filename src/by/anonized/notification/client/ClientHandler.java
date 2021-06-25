package by.anonized.notification.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import by.anonized.notification.common.Notification;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientHandler implements ITickHandler {
	private double a = -500.0D, b = -500.0D;
	private boolean d = false, e = false;
	private int c = 0, f = 0;
	public static ArrayList<Notification> g = new ArrayList<Notification>();
	public static Notification h = null;
	
	@ForgeSubscribe
	public void a(Pre event) {
		if (h != null && event.type == event.type.EXPERIENCE) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			//changeable
			int a = (int)this.a + 8; //X координата рендеринга, работает вместе с анимацией.
			int b = 8; //Y координата рендеринга
			int c = 178; //Ширина окна
			int d = 30; //максимальная длина текста
			int e = 11; //Расход между текстом(высота)
			float f = 1.0F; //Альфа-канал рендера
			//ЗДЕСЬ МОЖНО ПОМЕНЯТЬ ЧУТКА
			int g = (this.h.g ? ((this.h.b + this.h.h).length()) : this.h.b.length());
			if (g > d) {
				DrawHelper.b(b, a, c, 12 + (Notification.c(this.h.g ? this.h.h + " " + this.h.b : this.h.b, d) * e), DrawHelper.e(this.h.e[0], this.h.e[1], this.h.e[2]), f);
				a += 2;
				String[] h = new String[1 + (g / d)];
				for (int i = 0; i != (1 + (g / d)); i++) {
					if (this.h.g) {
						h[i] = (this.h.b + " " + this.h.h).substring(0 + (d * i), (d + (d * i) >= g ? g + 1: d + (d * i)));
					} else {
						h[i] = (this.h.b).substring(0 + (d * i), (d + (d * i) >= g ? g + 1 : d + (d * i)));
					}
				}
				for (String t : h) {
					DrawHelper.d(t, b + 2, a, Color.decode(DrawHelper.e(this.h.e[3], this.h.e[4], this.h.e[5])).getRGB());
					a += e;
				}
			} else {
				DrawHelper.b(b, a, c, 12, DrawHelper.e(this.h.e[0], this.h.e[1], this.h.e[2]), f);
				a += 2;
				DrawHelper.d(this.h.b, b + 2, a, Color.decode(DrawHelper.e(this.h.e[3], this.h.e[4], this.h.e[5])).getRGB());
				a += e;
			}
			int j = this.h.c.length();
			DrawHelper.b(b, a - 1, c, 15 + (Notification.c(this.h.c, d) * e), DrawHelper.e(this.h.e[6], this.h.e[7], this.h.e[8]), f);
			if (j > d) {
				a += 2;
				String[] k = new String[1 + (j / d)];
				for (int i = 0; i != (1 + (j / d)); i++) {
					k[i] = this.h.c.substring(0 + (d * i), (d + (d * i) >= j ? j : d + (d * i)));
				}
				for (String t : k) {
					DrawHelper.d(t, b + 2, a, Color.decode(DrawHelper.e(this.h.e[9], this.h.e[10], this.h.e[11])).getRGB());
					a += e;
				}
			} else {
				DrawHelper.b(b, a - 1, c, 15, DrawHelper.e(this.h.e[6], this.h.e[7], this.h.e[8]), f);
				a += 2;
				DrawHelper.d(this.h.c, b + 2, a, Color.decode(DrawHelper.e(this.h.e[9], this.h.e[10], this.h.e[11])).getRGB());
				a += e;
			}
			DrawHelper.b(b - 1, a, c + 2, 3, "#696969", f);
			DrawHelper.b(b - 1, a, (int)this.a(c, this.c, this.h.d), 3, "#00FF00", f);
			GL11.glPopMatrix();
		}
	}
	
	public static double a(double a, double b, double c) {
		return ((double)b/((double)c/a));
	}

	public void tickStart(EnumSet<TickType> type, Object... tickData) {}
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		this.f++;
		if (this.f % 100 == 0) {
			this.f = 0;
			if (this.a == this.b && this.h == null) {
				if (this.g.size() != 0) {
					this.h = this.g.get(0);
					this.g.remove(0);
					this.e = false;
					this.d = true;
					this.a = this.b;
					this.c = this.h.d;
					//ПОДГОТОВКА, ЗДЕСЬ УСТАНОВКА АНИМАЦИИ, МОЖНО ТУТ ЗВУК ПОСТАВИТЬ.
				}
			}
		}
		if (this.d) {
			if (this.a <= 0.0D) {
				this.a += 1.0D;
			} else {
				if (this.a > 0.0D) {
					this.a = 0.0D;
					this.d = false;
				}
			}
		} else {
			if (this.c != 0) {
				//НАЧАЛО КОДА ТАЙМЕРА
				this.c -= 50;
				if (this.c < 0) {
					this.c = 0;
				}
			}
		}
		
		if (this.c == 0) {
			if (!this.e) {
				this.e = true;
			} else if (this.e) {
				if (this.a == this.b) {
					this.h = null;
				} else {
					this.a -= 1.0D;
				}
			}
		}
	}


	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	public String getLabel() {
		return "NotificationClientHandler";
	}
}
