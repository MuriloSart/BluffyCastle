package game.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.game.GameManagers;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("BluffyCastle");
		config.useVsync(true);
		config.setWindowPosition(600, 280);
		config.setWindowedMode(1280, 720);
		
		new Lwjgl3Application(new GameManagers(), config);
	}
}
