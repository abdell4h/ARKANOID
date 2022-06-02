import java.awt.Color;
import java.awt.Graphics2D;

public class Azul extends Ladrillo {

	public Azul(Game game, int x, int y) {
		super(game, 1, x, y);
	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.decode("#2980B9"));// color principal
		g2d.fillRect(X, Y, WIDTH, HEIGHT);

	}

	@Override
	public boolean estadoVida() {
		if (getVida() <= 1) {
			// aumentar 10 segundos la velocidad a la raqueta
			new Thread(new Runnable() {
				public void run() {

					if (getVida() <= 1) {
						game.velocidadRaqueta = 11;
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						game.velocidadRaqueta = 6;
					}
				}
			}).start();
			return true;
		}
		return false;
	}

}