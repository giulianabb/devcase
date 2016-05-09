package br.com.devcase.treinamento.rihappy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RihappyApplication {	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		String content = restTemplate.getForObject("http://www.rihappy.com.br/nossas-lojas/dados", String.class);

		List<Loja> lojas = new ArrayList<Loja>();
		lojas = Loja.getLojas(content);
		
		System.out.println("\nLojas da RiHappy:\n");
		for(int i = 0; i < lojas.size(); i++) {
			System.out.println("Loja " + (i+1));
			System.out.println(lojas.get(i).toString());
		}

	}
}
