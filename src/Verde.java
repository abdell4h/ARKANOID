import java.awt.Color;
import java.awt.Graphics2D;

public class Verde extends Ladrillo {

	public Verde(Game game, int x, int y) {
		super(game, 3, x, y);

	}

	@Override
	public void paint(Graphics2D g2d) {

		if (getVida() == 1) {
			g2d.setColor(Color.decode("#82E0AA"));// color 1 vida restante
			g2d.fillRect(X, Y, WIDTH, HEIGHT);
		}
		if (getVida() == 2) {
			g2d.setColor(Color.decode("#2ECC71"));// color 2 vidas restantes
			g2d.fillRect(X, Y, WIDTH, HEIGHT);
		}
		if (getVida() == 3) {
			g2d.setColor(Color.decode("#239B56"));// color principal
			g2d.fillRect(X, Y, WIDTH, HEIGHT);
		}

	}

	@Override
	public boolean estadoVida() {
		if (getVida() <= 1) {
			return true;
		}
		return false;
	}

}