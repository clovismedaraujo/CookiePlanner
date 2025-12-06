package model.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Cookie;
import model.entities.Estoque;
import model.entities.Venda;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class VendaService {

	private static final String FILE_NAME = "vendas.json";
	public List<Venda> listaVendas = new ArrayList<>();

	private EstoqueService estoqueService = new EstoqueService();

	private final ObjectMapper objectMapper = new ObjectMapper();

	public VendaService() {
		loadVendasFromFile();
	}

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void loadVendasFromFile() {
		File file = new File(FILE_NAME);

		if (!file.exists() || file.length() == 0) {
			System.out.println("Arquivo " + FILE_NAME + " não encontrado ou vazio. Iniciando lista vazia.");
			return;
		}

		try (FileInputStream fis = new FileInputStream(file)) {

			TypeReference<List<Venda>> typeRef = new TypeReference<List<Venda>>() {
			};
			List<Venda> loadedList = objectMapper.readValue(fis, typeRef);

			if (loadedList != null) {
				listaVendas = loadedList;
				System.out.println("Vendas carregadas de " + FILE_NAME);
			}
		} catch (IOException e) {
			System.err.println("Erro ao carregar/parsear o JSON de vendas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void salvarVendasToFile() {
		File file = new File(FILE_NAME);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			objectMapper.writeValue(fos, listaVendas);
			System.out.println("Vendas salvas em " + FILE_NAME);
		} catch (IOException e) {
			System.err.println("Erro ao salvar vendas (Jackson): " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean registrarVenda(Cookie cookieVendido, int quantidade) {

		Estoque itemEstoque = estoqueService.getListaEstoque().stream()
				.filter(e -> e.getNomeCookie().equalsIgnoreCase(cookieVendido.getNomeCookie())).findFirst()
				.orElse(null);

		if (itemEstoque == null || itemEstoque.getQuantidade() < quantidade) {
			System.err.println("ERRO: Estoque insuficiente para " + cookieVendido.getNomeCookie());
			return false;
		}

		Venda novaVenda = new Venda(cookieVendido.getNomeCookie(), quantidade, cookieVendido.getPrecoCookie());

		estoqueService.registrarEntrada(cookieVendido.getNomeCookie(), -quantidade);

		listaVendas.add(novaVenda);
		salvarVendasToFile();

		return true;
	}
	
	public double calcularReceitaTotal() {
	    return listaVendas.stream()
	        .mapToDouble(Venda::getValorTotal)
	        .sum();
	}
}