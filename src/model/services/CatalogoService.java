package model.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Cookie;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class CatalogoService {

	private static final String FILE_NAME = "catalogo.json";
	public List<Cookie> listaCatalogo = new ArrayList<>();

	private final ObjectMapper objectMapper = new ObjectMapper();

	public CatalogoService() {
		loadCatalogoFromFile();
	}

	public List<Cookie> getListaCatalogo() {
		return listaCatalogo;
	}

	public static List<Cookie> getCatalogoEstatico() {
		return new CatalogoService().getListaCatalogo();
	}

	public void salvarNoCatalogo(Cookie obj) {
		listaCatalogo.add(obj);
		salvarCatalogoToFile();
	}

	public void salvarCatalogoToFile() {
		File file = new File(FILE_NAME);

		try (FileOutputStream fos = new FileOutputStream(file)) {

			objectMapper.writeValue(fos, listaCatalogo);
			System.out.println("Catálogo salvo em " + FILE_NAME);
		} catch (IOException e) { 
			System.err.println("Erro ao salvar o catálogo (Jackson): " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadCatalogoFromFile() {
		File file = new File(FILE_NAME);

		if (!file.exists() || file.length() == 0) {
			System.out.println("Arquivo " + FILE_NAME + " não encontrado ou vazio. Usando catálogo inicial.");
			return;
		}

		try (FileInputStream fis = new FileInputStream(file)) {

			TypeReference<List<Cookie>> typeRef = new TypeReference<List<Cookie>>() {
			};

			List<Cookie> loadedList = objectMapper.readValue(fis, typeRef);

			if (loadedList != null) {
				listaCatalogo = loadedList;
				System.out.println("Catálogo carregado de " + FILE_NAME);
			}
		} catch (IOException e) { 
			System.err.println("Erro ao carregar/parsear o JSON (Jackson): " + e.getMessage());
			e.printStackTrace();
		}
	}
}