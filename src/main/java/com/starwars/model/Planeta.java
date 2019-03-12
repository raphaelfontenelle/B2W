package com.starwars.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class Planeta implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nome;
	@NotEmpty
	private String clima;
	@NotEmpty
	private String terreno;
	
	private String id;
	
	private String filmes;
	
	public Planeta() {
		
		super();
		
	}
	
	public Planeta(String nome, String clima, String terreno, String id, String filmes) {
		
		super();
		
		this.setNome(nome);
		
		this.clima = clima;
		
		this.terreno = terreno;
		
		this.id = id;
		
		this.filmes = filmes;
	}
	

	
	public String getClima() {
		
		return clima;
	}
	
	public void setClima(String clima) {
		
		this.clima = clima;
	}
	
	public String getTerreno() {
		
		return terreno;
	}
	
	public void setTerreno(String terreno) {
		
		this.terreno = terreno;
		
	}

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}

	public String getFilmes() {
		
		return filmes;
	}

	public void setFilmes(String filmes) {
		
		this.filmes = filmes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}