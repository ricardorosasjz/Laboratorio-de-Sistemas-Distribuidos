package controllers;

/*
Ricardo Rosas Juárez
ID: 150371
mail: ricardo.rosasjz@udlap.mx
Guillermo del Río Acevedo
ID: 150313
mail: guillermo.delrioao@udlap.mx
*/

//Librerías para leer el archivo que contiene la información de los países europeos (proporcionado por la profesora)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import play.mvc.*;

import views.html.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.api.libs.json.*;

public class HomeController extends Controller {

	/*
	Método de muestra, hello world sencillo
	*/
    public Result index() {
        return ok(index.render("Hola mundo"));
    }
	/*
	Método que multiplica 2 enteros y regresa el resultado en formato Json
	*/
	public Result getMultiplicacion(int a, int b){
		ObjectNode result = Json.newObject(); // declaracion del objeto que será devuelto como resultado del método
		result.put("resultado", a*b );
		return ok(result);
	}

	/*
	Método que determina si una cadena es un palíndromo y que devuelve el resultado en formato json
	*/
	public Result getPalindromo(String word){
		ObjectNode result = Json.newObject();
		String myWord = word.replaceAll("\\s+","");
		String reverse = new StringBuffer(myWord).reverse().toString();
		result.put("resultado", reverse.equalsIgnoreCase(myWord));
		return ok(result);
	}

	/*
	Método que transforma una cantidad dada de pesos (double) en dólares mediante un tipo de cambio
	predeterminado. Devuelve el resultado en formato json
	*/
	public Result dolaresAPesos(double dolares){
		double tasaDeCambio = 18.515;
		ObjectNode result = Json.newObject();
		result.put("$" + dolares + " USD = ", "$" + dolares*tasaDeCambio + " M.N");
		return ok(result);
	
	}

	/*
	Método que transforma una cantidad de grados en escala farenheit a grados celsius.
	Devuelve el resultado en formato json
	*/
	public Result farenheitACentigrados(double farenheit){
		double centigrados = (farenheit-32) * 0.555555555555555555555556;
		ObjectNode result = Json.newObject();
		result.put(farenheit + "° F = ", centigrados + "° C");
		return ok(result);
	
	}

	/*
	Método que obtiene las coordenadas (latitud y longitud) de la embajada mexicana en un país europeo a través de la
	lectura de un documento predeterminado y otrogado por la profesora. Devuelve el resultado en formato json
	*/
	public Result embajadaMexico(String pais){
		ObjectNode result = Json.newObject();
		//Se obtiene el documento de los paises y las embajadas
        String csvFile = "/Users/Ricardo/Documents/UDLAP/6º Semestre/Lab Sistemas Distribuidos/Web services/paises.csv";
        String line = "";
        String cvsSplitBy = ","; // caracter que se utiliza para hacer el split del documento 
		Boolean encontrado = false; //bandera para saber si se ha encontrado el páis solicitado por el usuario
		String embajada1 = ""; //para guardar la primera coordenada
        String embajada2 = ""; //para guardar la segunda coordenada
		
		/*
		Procesamiento del documento y búsqueda de la embajada según el pais solicitado
		*/
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			
            while (encontrado == false) {

                // use comma as separator
                
				if ((line = br.readLine()) != null){
					String[] country = line.split(cvsSplitBy);
					if(pais.equals(country[0])){
					encontrado = true;
					embajada1 = country[1];
					embajada2 = country[2];
					}
				
				}
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
		result.put("resultado", "latitud: " + embajada1 + ", longitud: " + embajada2);
		return ok(result);
	}


	/*
	Método que obtiene una capital dado un país europeo (string). Devuelve el resultado en formato json
	*/
	public Result capitalEuropea(String pais){
		ObjectNode result = Json.newObject();
		//Se obtiene el documento de los paises y sus capitales
        String csvFile = "/Users/Ricardo/Documents/UDLAP/6º Semestre/Lab Sistemas Distribuidos/Web services/paises.csv";
        String line = "";
        String cvsSplitBy = ",";
		Boolean encontrado = false;
		String capital = "";
		
		/*
		Procesamiento del documento y búsqueda de la capital según el país solicitado
		*/
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			
            while (encontrado == false) {

                // use comma as separator
                
				if ((line = br.readLine()) != null){
					String[] country = line.split(cvsSplitBy);
					if(pais.equals(country[0])){
					encontrado = true;
					capital = country[3];
					}
				
				}
            }
        }
		catch (IOException e) {
            e.printStackTrace();
        }

	result.put("resultado", capital);
	return ok(result);
	}
	
}
