import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Pelota pelota = new Pelota(this);
	Raqueta raqueta = new Raqueta(this);
	ArrayList<Ladrillo> arrayLadrillos = new ArrayList<Ladrillo>();
	JsonUpdate datos = new JsonUpdate("puntuacion.json");
	int velocidadPelota = 3;
	int velocidadRaqueta = 6;
	int puntuacion = 0;
	String usuario = "Anónimo";

	private int getScore() {
		return puntuacion;
	}

	public Game() {

		crearLadrillos(arrayLadrillos);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				raqueta.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				raqueta.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void move() {
		pelota.move();
		raqueta.move();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.decode("#273746"));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		pelota.paint(g2d);
		raqueta.paint(g2d);

		for (int i = 0; i < arrayLadrillos.size(); i++) {

			arrayLadrillos.get(i).paint(g2d);

		}

		g2d.setColor(Color.decode("#E5E7E9"));
		g2d.setFont(new Font("DialogInput", Font.BOLD, 17));
		g2d.drawString(String.valueOf("Score: " + getScore()), 10, 650);

		g2d.setColor(Color.decode("#E5E7E9"));
		g2d.setFont(new Font("DialogInput", Font.BOLD, 17));
		g2d.drawString(String.valueOf("Vida Pelota: " + pelota.vidaPelota), 160, 650);

		g2d.setColor(Color.decode("#E5E7E9"));
		g2d.setFont(new Font("DialogInput", Font.BOLD, 17));
		g2d.drawString(String.valueOf("Jugador: " + usuario), 350, 650);

	}

	public void finPartida() {
		// Este metodo guarda partida del jugador
		datos.guardarUsuario(usuario, puntuacion);

		String[] arrayNombres = datos.lecturaNombres();

		for (int i = 0; i < arrayNombres.length; i++) {
			for (int j = 0; j < arrayNombres.length; j++) {
				if (datos.lecturaPuntuacion(arrayNombres[i]) > datos.lecturaPuntuacion(arrayNombres[j])) {
					String aux = arrayNombres[i];
					arrayNombres[i] = arrayNombres[j];
					arrayNombres[j] = aux;
				}
			}

		}

		JOptionPane.showMessageDialog(this,
				usuario + " has conseguido " + getScore() + " puntos en esta partida.\n" + "Top 3 mejores partidas:\n"
						+ arrayNombres[0] + ": " + datos.lecturaPuntuacion(arrayNombres[0]) + "\n" + arrayNombres[1]
						+ ": " + datos.lecturaPuntuacion(arrayNombres[1]) + "\n" + arrayNombres[2] + ": "
						+ datos.lecturaPuntuacion(arrayNombres[2]) + "\n",
				"Resumen \n", JOptionPane.INFORMATION_MESSAGE);

		System.exit(ABORT);

	}

	public void introducirNombre() {
		do {
			usuario = JOptionPane.showInputDialog("Introduce el nombre:");
		} while (usuario.isEmpty());
	}

	public void emptyArrayLadrillos() {
		if (arrayLadrillos.isEmpty()) {
			finPartida();
		}
	}

	public void crearLadrillos(ArrayList<Ladrillo> arrayLadrillos) {
		// podria ingresar por parametros x & y
		for (int x = 1; x < 500; x++) {
			for (int y = 0; y < 300; y++) {

				int rand = (int) Math.floor(Math.random() * 3);

				if (rand == 0) {

					Ladrillo ladrillo = new Azul(this, x, y);
					arrayLadrillos.add(ladrillo);
				}
				if (rand == 1) {

					Ladrillo ladrillo = new Rojo(this, x, y);
					arrayLadrillos.add(ladrillo);
				}
				if (rand == 2) {
					Ladrillo ladrillo = new Verde(this, x, y);
					arrayLadrillos.add(ladrillo);
				}

				y += 30;
			}
			x += 90;
		}
	}

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame("ARKANOID");
		Game game = new Game();
		game.introducirNombre();
		frame.add(game);
		frame.setSize(561, 700);
		frame.setLocationRelativeTo(null); // frame.setLocation(100,100);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.emptyArrayLadrillos();
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}

}