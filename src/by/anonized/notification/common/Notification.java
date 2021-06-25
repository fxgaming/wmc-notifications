package by.anonized.notification.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;

public class Notification {
	public String a;
	public String b;
	public String c;
	public int d;
	public int[] e;
	public boolean f;
	public boolean g;
	public String h;

	public Notification(String a, String b, String c, int d, int[] e, boolean f, boolean g, DateFormat h) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h.toString();
	}

	public Notification(String a, String b, String c, int d, int[] e, boolean f, boolean g, String h) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
	}

	public static class DateFormat {
		public String a;
		
		public DateFormat(String a) {
			this.a = a;
		}
		
		@Override
		public String toString() {
			Calendar a = Calendar.getInstance();
			String b = this.a.replace("YEAR", a.YEAR + "").replace("MONTH", a.MONTH + "").replace("WEEK", a.WEEK_OF_MONTH + "").replace("WEEKDAY", a.DAY_OF_WEEK + "").replace("DAY", a.DAY_OF_MONTH + "").replace("HOUR", a.HOUR_OF_DAY + "").replace("MINUTE", a.MINUTE + "").replace("SECOND", a.SECOND + "").replace("MILLISECOND", a.MILLISECOND + "");
			return b;
		}
	}

	public static boolean a(String a) {
		File b = new File("notifications/");
		if (!b.exists()) {
			b.mkdirs();
		}
		for (File c : b.listFiles()) {
			if (c.isFile()) {
				if (c.getName().equalsIgnoreCase(a + ".ntf")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Notification b(String file) {
		File a = new File("notifications/" + file + ".ntf");
		BufferedReader b = null;
		FileReader c = null;
		try {
			b = new BufferedReader(c = new FileReader(a));
			String d = file;
			String e = b.readLine().split("Title: ")[1];
			String f  = b.readLine().split("Text: ")[1];
			Integer g = Integer.valueOf(b.readLine().split("Notify time: ")[1]);
			String[] h = b.readLine().split("RGB title: ")[1].split(", ");
			String[] i = b.readLine().split("RGB title text: ")[1].split(", ");
			String[] j = b.readLine().split("RGB background: ")[1].split(", ");
			String[] k = b.readLine().split("RGB text: ")[1].split(", ");
			int[] l = new int[]{Integer.valueOf(h[0]), Integer.valueOf(h[1]), Integer.valueOf(h[2]), Integer.valueOf(i[0]), Integer.valueOf(i[1]), Integer.valueOf(i[2]), Integer.valueOf(j[0]), Integer.valueOf(j[1]), Integer.valueOf(j[2]), Integer.valueOf(k[0]), Integer.valueOf(k[1]), Integer.valueOf(k[2])};
			Boolean m = b.readLine().split("Enable exit button: ")[1].toLowerCase().equals("true");
			Boolean n = b.readLine().split("Enable date: ")[1].toLowerCase().equals("true");
			String o = b.readLine().split("Date or Date format: ")[1];
			DateFormat p = null;
			if (o.split(">")[0].equals("FORMAT")) {
				p = new DateFormat(o.split(">")[1]);
			}
			Notification q;
			if (p == null) {
				q = new Notification(d, e, f, g, l, m, n, o);
			} else {
				q = new Notification(d, e, f, g, l, m, n, p);
			}
			return q;
		} catch (Exception r) {
			return null;
		} finally {
			try {
				b.close();
				c.close();
			} catch (Exception s) {
				return null;
			}
		}
	}
	
	public static int c(String a, int b) {
		return a.length() / b;
	}
}
