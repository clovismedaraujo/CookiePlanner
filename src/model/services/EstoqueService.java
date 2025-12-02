package model.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.entities.Estoque;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class EstoqueService {

	private static final String FILE_NAME = "estoque.json";
	public List<Estoque> listaEstoque = new ArrayList<>();

	private final ObjectMapper objectMapper = new ObjectMapper();

	public EstoqueService() {
		loadEstoqueFromFile(); 
	}
	
	public List<Estoque> getListaEstoque() {
	    return listaEstoque;
	}
	
	public void loadEstoqueFromFile() {
		File file = new File(FILE_NAME);
		
		if (!file.exists() || file.length() == 0) {
			System.out.println("Arquivo " + FILE_NAME + " não encontrado ou vazio. Iniciando lista vazia.");
			return; 
		}

		try (FileInputStream fis = new FileInputStream(file)) {
			
			TypeReference<List<Estoque>> typeRef = new TypeReference<List<Estoque>>() {};
			List<Estoque> loadedList = objectMapper.readValue(fis, typeRef);
			
			if (loadedList != null) {
				listaEstoque = loadedList;
				System.out.println("Estoque carregado de " + FILE_NAME);
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar/parsear o JSON de estoque: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void salvarEstoqueToFile() {
		File file = new File(FILE_NAME);
	    try (FileOutputStream fos = new FileOutputStream(file)) {
	        objectMapper.writeValue(fos, listaEstoque);
	        System.out.println("Estoque salvo em " + FILE_NAME);
	    } catch (IOException e) {
	        System.err.println("Erro ao salvar o estoque (Jackson): " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Registra uma entrada de estoque, atualiza a quantidade e persiste.
	 */
	public void registrarEntrada(String nomeCookie, int quantidade) {
		Optional<Estoque> itemExistente = listaEstoque.stream()
			.filter(e -> e.getNomeCookie().equalsIgnoreCase(nomeCookie))
			.findFirst();

		if (itemExistente.isPresent()) {
			Estoque item = itemExistente.get();
			item.setQuantidade(item.getQuantidade() + quantidade);
		} else {
			listaEstoque.add(new Estoque(nomeCookie, quantidade));
		}
		
		salvarEstoqueToFile();
	}
}
