import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUpdate {
	private String ruta;// ="puntuacion.json";
	private JSONObject jObjeto;
	private JSONArray jArray;

	public JsonUpdate(String ruta) {

		this.ruta = ruta;
		try {
			FileReader lectura = new FileReader(ruta);

			// leer el archivo de la ruta y lo transforma a un oobjeto java
			JSONParser parser = new JSONParser();
			jObjeto = (JSONObject) parser.parse(lectura);
			// leer el archivo y pasarlo a json cuando tengamos estos, coger el array con el
			// nombre puntuacion
			jArray = (JSONArray) jObjeto.get("puntuacion");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] lecturaNombres() {
		// array de strings para guaardar todos los nombres de usuarios
		String[] usuarios = new String[jArray.size()];
		// aqui recorro todos los objetos del json array
		for (int i = 0; i < jArray.size(); i++) {
			// guardamos el objeto del usuario en concreto
			JSONObject usuario = (JSONObject) jArray.get(i);
			// obtener el valor del atributo del nombre objeto y lo guardamos en el array de
			// strings
			usuarios[i] = String.valueOf(usuario.get("nombre"));
		}
		return usuarios;
	}

	public int lecturaPuntuacion(String nombreUsuario) {
		// aqui recorro todos los objetos del json array
		for (int i = 0; i < jArray.size(); i++) {
			// guardamos el objeto del usuario en concreto del array json
			JSONObject usuario = (JSONObject) jArray.get(i);
			// Condición: miramos el usuario actual contiene el nombre de usuario por
			// parametro
			if (String.valueOf(usuario.get("nombre")).equalsIgnoreCase(nombreUsuario)) {
				// OBTENEMOS LA PUNTUACION Y LA PASAMOS A STRING
				String puntuacion = String.valueOf(usuario.get("puntos")); // "puntos"
				// CONVERTIMOS EL STRING DE PUNTUACION A INT
				return Integer.parseInt(puntuacion);
			}
		}
		return -1;
	}

	// devolcer el json object de un usuario en concreto
	public JSONObject getUser(String nombreUsuario) {
		for (int i = 0; i < jArray.size(); i++) {
			JSONObject usuario = (JSONObject) jArray.get(i);

			if (String.valueOf(usuario.get("nombre")).equalsIgnoreCase(nombreUsuario)) {
				return usuario;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void guardarUsuario(String nombreUsuario, int puntuacion) {
		JSONObject usuario = getUser(nombreUsuario);

		if (usuario == null) {
			usuario = new JSONObject();
			usuario.put("nombre", nombreUsuario);
			usuario.put("puntos", puntuacion);
			jArray.add(usuario);
		} else {
			usuario.put("puntos", puntuacion);
		}
		try {

			FileWriter escritura = new FileWriter(ruta);
			escritura.write(jObjeto.toJSONString());
			escritura.close();
		} catch (Exception e) {

		}
	}
}
