import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Ladrillo {

	protected Game game;
	private int vida;
	protected static final int WIDTH = 90;
	protected static final int HEIGHT = 30;
	protected int X = 0;
	protected int Y = 0;

	public Ladrillo(Game game, int vida, int x, int y) {
		super();
		this.game = game;
		this.vida = vida;
		X = x;
		Y = y;
	}

	public abstract void paint(Graphics2D g);

	public abstract boolean estadoVida();

	public Rectangle getBounds() {
		return new Rectangle(X, Y, WIDTH, HEIGHT);

	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		this.Y = y;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
