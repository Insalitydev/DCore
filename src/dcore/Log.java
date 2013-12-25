package dcore;

import java.util.ArrayList;

import org.newdawn.slick.Color;

public class Log {

	public static final byte			system		= 0;
	public static final byte			game		= 1;
	public static final byte			battle		= 2;
	public static final byte			load		= 3;
	public static final byte			debug		= 4;

	private static boolean				systemShow	= true;
	private static boolean				gameShow	= true;
	private static boolean				battleShow	= true;
	private static boolean				loadShow	= true;
	private static boolean				debugShow	= true;

	private static final byte			lines		= 5;
	private static ArrayList<String>	gameLog		= new ArrayList<String>();

	public static void print(byte type, String msg) {

		// Проверяем, выводим ли эту информацию:
		if (!systemShow && type == system)
			return;
		if (!gameShow && type == game)
			return;
		if (!battleShow && type == battle)
			return;
		if (!loadShow && type == load)
			return;
		if (!debugShow && type == debug)
			return;

		if (type == system) {
			System.out.print("[System]: ");
		}
		if (type == game) {
			System.out.print("[Game]: ");
			gameLog.add(msg);
		}
		if (type == battle) {
			System.out.print("[Battle]: ");
		}
		if (type == load) {
			System.out.print("[Load]: ");
		}
		if (type == debug) {
			System.out.print("[Debug]: ");
		}

		System.out.println(msg);
	}

	public static void draw() {
		for (int i = 1; i <= lines; i++) {
			if (gameLog.size() - i >= 0)
				Font.hondaSmall.drawString(0, Main.GAME_HEIGHT - Font.hondaSmall.getHeight() *i, gameLog.get(gameLog.size() - i), Color.blue);
		}
	}

}
