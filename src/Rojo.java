import java.awt.Color;
import java.awt.Graphics2D;

public class Rojo extends Ladrillo {
	boolean cambioColor = false;

	public Rojo(Game game, int x, int y) {
		super(game, 2, x, y);

	}

	@Override
	public void paint(Graphics2D g2d) {

		if (getVida() == 0) {
			g2d.setColor(Color.decode("#566573"));// color 0 vida restante
			g2d.fillRect(X, Y, WIDTH, HEIGHT);
		}
		if (getVida() == 1) {
			g2d.setColor(Color.decode("#EC7063"));// color 1 vida restante
			g2d.fillRect(X, Y, WIDTH, HEIGHT);
		}
		if (getVida() == 2) {
			g2d.setColor(Color.decode("#B03A2E"));// color principal
			g2d.fillRect(X, Y, WIDTH, HEIGHT);
		}

	}

	@Override
	public boolean estadoVida() {
		if (getVida() < 1) {
			return true;
		}
		return false;
	}

}