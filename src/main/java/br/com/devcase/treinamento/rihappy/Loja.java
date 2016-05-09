package br.com.devcase.treinamento.rihappy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loja {
	private String uf;
	private String cidade;
	private String nome;
	private String endereco;
	private String telefone;
	
	//Setters e getters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() +  "\n"
				+ "Endereço: " + this.getEndereco() + "\n"
				+ "Cidade: " + this.getCidade() + "\n"
				+ "Estado: " + this.getUf() + "\n"
				+ "Telefone: " + this.getTelefone() + "\n";
	}
	
	/**
	 * Retorna uma lista de Lojas de acordo com o conteudo da String recebida.
	 * Usa regex para separar as lojas individualmente.
	 * @param conteudoTotal
	 * @return
	 */
	public static List<Loja> getLojas(String conteudoTotal) {
		String patternLoja = "<loja>([\\s\\S]*?)<\\/loja>";
		List<Loja> lojas = new ArrayList<Loja>();
		List<String> conteudoDeCadaLoja = getMatch(patternLoja, conteudoTotal);

		for(int i = 0; i < conteudoDeCadaLoja.size(); i++) {
			lojas.add(getLoja(conteudoDeCadaLoja.get(i)));

		}
		return lojas;
	}
	
	/**
	 * Retorna um objeto Loja com os atributos 
	 * preenchidos de acordo com o conteúdo da String recebida.
	 * Usa regex para separar cada atributo individualmente.
	 * @param conteudoDaLoja
	 * @return
	 */
	private static Loja getLoja(String conteudoDaLoja) {
		String patternUF = "<uf>([\\s\\S]*?)<\\/uf>";
		String patternCidade =  "<cidade>([\\s\\S]*?)<\\/cidade>";
		String patternNome = "<nome>([\\s\\S]*?)<\\/nome>";
		String patternEndereco = "<endereco>([\\s\\S]*?)<\\/endereco>";
		String patternTelefone = "<telefone>([\\s\\S]*?)<\\/telefone>";
		
		Loja loja = new Loja();
		
		loja.setUf(getMatch(patternUF, conteudoDaLoja).get(0));
		loja.setCidade(getMatch(patternCidade, conteudoDaLoja).get(0));
		loja.setNome(getMatch(patternNome, conteudoDaLoja).get(0));
		loja.setEndereco(getMatch(patternEndereco, conteudoDaLoja).get(0));
		loja.setTelefone(getMatch(patternTelefone, conteudoDaLoja).get(0));
		
		return loja;
	}
	
	/**
	 * Recebe como entrada um pattern e um texto, e retorna as 
	 * strings que deram match para este pattern.
	 * @param pattern
	 * @param text
	 * @return
	 */
	private static List<String> getMatch(String pattern, String text) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		List<String> conteudo = new ArrayList<String>();
		while(m.find()) {
			conteudo.add(m.group(1));
		}
		return conteudo;
	}
}
