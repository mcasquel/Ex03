package Test;


import Model.Pais;
import Service.PaisService;

import org.junit.runners.MethodSorters;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Teste {

	private Pais pais,copia;
	private PaisService service;
	static int id = 1;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Setup");
		pais = new Pais(id,"Brasil",8541800,57841.00);
		copia = new Pais(id,"Brasil",8541800,57841.00);
		service = new PaisService();
	}
	
	@Test
	public void testA() {
		System.out.println("Criando");
		service.criar(pais);
		id = pais.getId();
		assertEquals(pais.getId(),copia.getId(),"Teste de criacao");
	}
	

	
	@Test
	public void testB() {
		System.out.println("Atualizar");

		pais.setNome("Atualizado");
		copia.setNome("Atualizado");
		
		service.alterarNome(pais, "Nome");
		service.selectPais(pais);
		
		assertEquals(pais.getNome(),copia.getNome(),"Teste Atualziar");
		
	}
	
	public void testC() {
		
		System.out.println("Teste Carregar");
		service.selectPais(pais);
		assertEquals(pais.getId(),copia.getId(),"Teste de criacao");
	}
	
	@Test
	public void testD() {
		System.out.println("Excluir Pais");
		service.deletar(pais);
		copia.setId(-1);
		
		service.selectPais(pais);
		assertEquals(pais.getId(),copia.getId(),"Teste de criacao");
	}

	@Test
	public void testE() {
		System.out.println("Maior populacao");
		service.habitantes(pais);
		
		long pop = pais.getPopulacao();
		copia.setPopulacao(pop);
		assertEquals(pais.getPopulacao(),copia.getPopulacao(),"Testando maior populacao");
	}
	
	@Test
	public void testF() {
		System.out.println("Menor Area");
		service.menorArea(pais);
		double area = pais.getArea();		
		copia.setArea(area);
		
		assertEquals(pais.getArea(),copia.getArea(),"Testando menor area");
	}
	
	@Test
	public void testG() {
		System.out.println("Vetor de paises");
		String[] array = service.arrayPaises();
		
		assertEquals(array.length,3,"Testando Vetor de paises");
	}
	
	
}