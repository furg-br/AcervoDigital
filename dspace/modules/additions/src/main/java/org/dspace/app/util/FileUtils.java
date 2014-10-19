package org.dspace.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

/**
 * Classe utilitária utilizada como elemento facilitador e manuseador de arquivos
 * @author Márcio Ribeiro Gurgel do Amaral (marcio.rga@gmail.com)
 *
 */
public class FileUtils {

	
	/**
	 * Converte conteúdo de dado arquivo em String
	 * @param fileName Nome do arquivo (presente no class classpath)
	 * @return Conteúdo do arquivo em forma de String
	 * @throws IOException
	 */
	public static String getAsString(String fileName) throws IOException {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()));
		String line = null;
		StringBuilder fileString = new StringBuilder();
		while((line = bufferedReader.readLine()) != null) {
			
			fileString.append(line);
		}
		
		bufferedReader.close();
		return fileString.toString();
		
	}
	
}
