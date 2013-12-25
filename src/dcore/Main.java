package dcore;

import static org.lwjgl.opengl.GL11.*;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Начальный класс. Инициализация всего проекта и начало игрового цикла
 * 
 * @see Game
 */
public class Main extends Applet {
	private static final long	serialVersionUID	= 1L;
	public static final int		GAME_WIDTH			= 800;
	public static final int		GAME_HEIGHT			= 600;
	public static final int FPS = 60;
	
	public static Game			game;
	/** На канвасе создает окно openGL и там уже всё рисуем */
	Canvas						displayParent;

	/** Thread which runs the main game loop */
	Thread						gameThread;

	/** is the game loop running */
	public static boolean		running				= false;

	public void init() {
		Log.print(Log.system, "Init applet window...");
		setLayout(new BorderLayout());
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setBackground(Color.BLUE);

		try {
			displayParent = new Canvas() {
				private static final long	serialVersionUID	= -2475252538422795530L;

				public final void addNotify() {
					super.addNotify();
					startLWJGL();
				}

				public final void removeNotify() {
					stopLWJGL();
					super.removeNotify();
				}
			};

			add(displayParent);
			displayParent.setSize(GAME_WIDTH, GAME_HEIGHT);
			displayParent.setFocusable(true);
			displayParent.requestFocus();
			displayParent.setIgnoreRepaint(true);
			setVisible(true);
		}
		catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException("Unable to create display");
		}
	}

	public void startLWJGL() {
		gameThread = new Thread() {
			public void run() {
				Log.print(Log.system, "Init LWJGL window...");
				running = true;
				try {
					Display.setParent(displayParent);
					Display.create();
					initGL();
					game = new Game();
				}
				catch (LWJGLException e) {
					e.printStackTrace();
					return;
				}
				Log.print(Log.system, "Starting the main Game Loop");
				game.gameLoop();
			}
		};
		gameThread.start();
	}

	/**
	 * Tell game loop to stop running, after which the LWJGL Display will be destoryed. The main thread will wait for the Display.destroy().
	 */
	private void stopLWJGL() {
		running = false;
		try {
			gameThread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Applet Destroy method will remove the canvas, before canvas is destroyed it will notify stopLWJGL() to stop the main game loop and to destroy the Display
	 */
	public void destroy() {
		remove(displayParent);
		super.destroy();
	}

	protected void initGL() {
		Log.print(Log.system, "Init openGL...");
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glOrtho(0, GAME_WIDTH, GAME_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		GL11.glEnable(GL_BLEND);
		GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
}