import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pelota {
	private static final int DIAMETER = 30;

	int x = 30;
	int y = 320;
	int xa = 3;
	int ya = 3;
	int vidaPelota = 3;
	private Game game;

	public Pelota(Game game) {
		this.game = game;
	}

	void move() {

		if (x + xa < 0)
			xa = game.velocidadPelota;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.velocidadPelota;
		else if (y + ya < 0)
			ya = game.velocidadPelota;
		else if (y + ya > game.getHeight() - DIAMETER) {
			vidaPelota--;
			x = 15;
			y = 330;

			if (vidaPelota <= 0) {
				game.finPartida();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (collisionRacquet()) {
			ya = -game.velocidadPelota;
			y = game.raqueta.getTopY() - DIAMETER;
			// game.speed++;
		} else if (collisionLadrillo()) {
			ya = -ya;
			xa = +xa;// +game.speed;
		}

		x = x + xa;
		y = y + ya;

	}

	private boolean collisionRacquet() {
		return game.raqueta.getBounds().intersects(getBounds());
	}

	private boolean collisionLadrillo() {
		// recorre todo el array de latrillos y retorna un booleano si ha colisionado un
		// true o si no se ha colisionado false
		// for (Ladrillo l : game.arrayLadrillos) {
		for (int i = 0; i < game.arrayLadrillos.size(); i++) {
			if (game.arrayLadrillos.get(i).getBounds().intersects(getBounds())) {
				if ((game.arrayLadrillos.get(i).estadoVida() == true) && (game.arrayLadrillos.get(i) instanceof Verde)
						|| (game.arrayLadrillos.get(i) instanceof Azul)) {

					game.puntuacion += 50;
					game.arrayLadrillos.remove(i);
					return true;
				}
				// cuando los ladrillos rojos se hayan eliminado y estan cayendo, no reboten con
				// la pelota
				// ya que estan eliminados
				if (game.arrayLadrillos.get(i).estadoVida() == true && (game.arrayLadrillos.get(i) instanceof Rojo)) {
					return false;
				} else {
					int vida = game.arrayLadrillos.get(i).getVida() - 1;
					game.arrayLadrillos.get(i).setVida(vida);
					game.puntuacion += 10;
					return true;
				}

			}
		}

		return false;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.decode("#F4D03F"));
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}