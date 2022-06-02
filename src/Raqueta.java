import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Raqueta {
	private static final int Y = 600;
	private static final int WIDTH = 110;
	private static final int HEIGHT = 25;
	int x = 230;
	int xa;
	private Game game;

	public Raqueta(Game game) {
		this.game = game;
	}

	public void move() {

		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {

			x = x + xa;
		}

		ladrillosCayendo();

	}

	public void ladrillosCayendo() {
		for (int i = 0; i < game.arrayLadrillos.size(); i++) {
			if ((game.arrayLadrillos.get(i) instanceof Rojo) && (game.arrayLadrillos.get(i).estadoVida() == true)) {
				if (game.arrayLadrillos.get(i).getBounds().intersects(getBounds())) {
					game.finPartida();
				}
				if (game.arrayLadrillos.get(i).getY() > 600) {
					game.arrayLadrillos.remove(i);
					game.puntuacion += 50;
				} else {
					int pos = game.arrayLadrillos.get(i).getY();
					game.arrayLadrillos.get(i).setY(pos + 2);

				}
			}
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.decode("#DC7633"));
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.velocidadRaqueta;// -game.speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.velocidadRaqueta;// game.speed;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT + 20;
	}
}