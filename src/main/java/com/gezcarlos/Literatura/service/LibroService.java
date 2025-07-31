package com.gezcarlos.Literatura.service;

import com.gezcarlos.Literatura.client.ConsumoAPI;
import com.gezcarlos.Literatura.model.DatosLibro;
import com.gezcarlos.Literatura.model.Idioma;
import com.gezcarlos.Literatura.model.Libro;
import com.gezcarlos.Literatura.model.Persona;
import com.gezcarlos.Literatura.repository.IdiomaRepository;
import com.gezcarlos.Literatura.repository.LibroRepository;
import com.gezcarlos.Literatura.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibroService {
    private final String URL_BASE = "https://gutendex.com/books";

    @Autowired
    ConsumoAPI consumoApi;

    @Autowired
    LibroRepository repositorioLibro;

    @Autowired
    PersonaRepository repositorioPersona;

    @Autowired
    IdiomaRepository repositorioIdioma;

    private final ConvierteDatos conversor = new ConvierteDatos();

    public LibroService() {

    }

    @Transactional
    public void buscarLibroPortitulo(String nombreLibro) {
        String url = URL_BASE + "/?search=" + nombreLibro.replace(" ", "%20");
        System.out.println("--------");
        System.out.println("Espere unos momentos....:");
        System.out.println("--------");
        String json = consumoApi.obtenerDatos(url);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        System.out.println("1.- url: " + url + "; total de libros obtenidos: " + datos.total() + ". Link siguiente: " + datos.linkSiguiente() + ". Link anterior: " + datos.linkAnterior());
        System.out.println("----------------------------------------------------------------------------------------------\n\n");
        System.out.println("--------");
        System.out.println("--------");

        datos.resultadosLibro().forEach(datosLibro -> {
            // Verifica por idLibro primero (más eficiente si tienes índice)
            Optional<Libro> libroExistentePorId = repositorioLibro.findByIdLibro(datosLibro.idLibro());

            // Si no existe por id, verifica por título
            if (libroExistentePorId.isEmpty()) {
                Optional<Libro> libroExistentePorTitulo = repositorioLibro.findByTitulo(datosLibro.titulo());
                if (libroExistentePorTitulo.isPresent()) {
                    System.out.println("--------");
                    System.out.printf("-------- El Libro [ %s ] ya existe (mismo título).%n", libroExistentePorTitulo.get().getTitulo());
                    System.out.println("--------");
                    return;
                }
            } else {
                System.out.println("--------");
                System.out.printf("-------- El Libro [ %s ] ya existe (mismo ID).%n", libroExistentePorId.get().getTitulo());
                System.out.println("--------");
                return;
            }

            // Si llegamos aquí, el libro no existe y podemos crearlo
            Libro nuevoLibro = new Libro(datosLibro);
            nuevoLibro.setId(null);

            // Procesa los autores (tu código existente)
            List<Persona> autoresFinales = new ArrayList<>();
            for (Persona autor : nuevoLibro.getAutores()) {
                Persona existente = repositorioPersona.findByNombre(autor.getNombre());
                if (existente != null) {
                    existente.getLibros().add(nuevoLibro);
                    autoresFinales.add(existente);
                } else {
                    autor.setLibros(List.of(nuevoLibro));
                    autoresFinales.add(autor);
                }
            }
            nuevoLibro.setAutores(autoresFinales);

            // Procesa los idiomas (tu código existente)
            List<Idioma> idiomasFinales = new ArrayList<>();
            for (Idioma idioma : nuevoLibro.getIdiomas()) {
                Optional<Idioma> idiomaExistente = repositorioIdioma.findById(idioma.getCodigo());
                if (idiomaExistente.isPresent()) {
                    idiomasFinales.add(idiomaExistente.get());
                } else {
                    idioma.setNombre(obtenerNombreIdioma(idioma.getCodigo()));
                    repositorioIdioma.save(idioma);
                    idiomasFinales.add(idioma);
                }
            }
            nuevoLibro.setIdiomas(idiomasFinales);

            repositorioLibro.save(nuevoLibro);
            imprimeLibro(nuevoLibro);
        });
    }

    private String obtenerNombreIdioma(String codigo) {
        Map<String, String> nombresIdiomas = new HashMap<>();
        nombresIdiomas.putAll(Map.of(
                "en", "Inglés",
                "tl", "Tagalo",
                "enm", "Inglés medio",
                "fi", "Finés / Finlandés",
                "pt", "Portugués",
                "hu", "Húngaro",
                "is", "Islandés",
                "pl", "Polaco",
                "el", "Griego",
                "zh", "Chino"

        ));
        nombresIdiomas.putAll(Map.of(
                "it", "Italiano",
                "es", "Español",
                "la", "Latín",
                "de", "Alemán",
                "eo", "Esperanto",
                "nl", "Neerlandés / Holandés",
                "fr", "Francés"
        ));

        return nombresIdiomas.getOrDefault(codigo, "Desconocido (" + codigo + ")");
    }

    public void buscarTodosLosLibrosRegistrados() {
        List<Libro>  libros = repositorioLibro.findAll();
        if (libros.isEmpty()) {
            System.out.println("--------");
            System.out.println("-------- No existen libros registrados.");
            System.out.println("--------");
        } else {
            libros.forEach(this::imprimeLibro);
        }
    }

    public void buscarAutoresRegistrados() {
        List<Persona> autores = repositorioPersona.findAll();
        if (autores.isEmpty()) {
            System.out.println("--------");
            System.out.println("-------- No existen autores registrados.");
            System.out.println("--------");
        } else {
            autores.forEach(this::imprimeAutor);
        }
    }

    public void buscarAutoresRegistradosPorAnio(Integer year) {
        List<Persona> autores = repositorioPersona.findByAnioAutor(year);
        if (autores.isEmpty()) {
            System.out.println("--------");
            System.out.println("-------- No existen autores registrados.");
            System.out.println("--------");
        } else {
            autores.forEach(this::imprimeAutor);
        }
    }

    public void obtenerTodosLosIdiomas() {
        List<Idioma> idiomas = repositorioIdioma.findAll();
        if (idiomas.isEmpty()) {
            System.out.println("--------");
            System.out.println("-------- No existen idiomas registrados.");
            System.out.println("--------");
            return;
        }

        //idiomas.forEach(System.out::println);

        System.out.println("--------");
        System.out.println("-------- Los idiomas disponibles son: ");
        System.out.println("-------- Ejemplos:  en / en,es / es,fr,en / de,zh,fi,pt ");
        System.out.println("--------");
        int count = 0;
        for (Idioma idioma : idiomas) {
            System.out.printf("%-25s", idioma.toString()); // ajusta el ancho si es necesario
            count++;
            if (count % 5 == 0) {
                System.out.println(); // salto de línea cada 5 idiomas
            }
        }
        if (count % 5 != 0) {
            System.out.println(); // salto final si no fue múltiplo exacto
        }
    }

    public void buscarLibrosPorIdiomas(String misIdiomas) {
        List<String> codigos = Arrays.stream(misIdiomas.split(","))
                .map(String::trim)
                .toList();
        List<Libro>  libros = repositorioLibro.findByIdiomas(codigos);
        if (libros.isEmpty()) {
            System.out.println("--------");
            System.out.println("-------- No existen libros registrados.");
            System.out.println("--------");
        } else {
            libros.forEach(this::imprimeLibro);
        }
    }

    public void buscarLibrosPorIdiomasPaginado(String misIdiomas) {
        List<String> codigos = Arrays.stream(misIdiomas.split(","))
                .map(String::trim)
                .toList();

        int pagina = 0;
        int tamanio = 5;
        Page<Libro> libros;
        Scanner scanner = new Scanner(System.in);

        do {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            libros = repositorioLibro.findByIdiomas(codigos, pageable);
            if (libros.isEmpty() && pagina == 0) {
                System.out.println("--------");
                System.out.println("-------- No existen libros registrados.");
                System.out.println("--------");
                return;
            }

            System.out.println("--------");
            System.out.println("\nPágina " + (pagina + 1) + " de " + libros.getTotalPages());
            System.out.println("--------");
            libros.getContent().forEach(this::imprimeLibro);

            if (!libros.isLast()) {
                System.out.println("\nPresiona 'c' o 'q' seguido de Enter para continuar o para salir:");
                String entrada = scanner.nextLine();
                if (entrada.equalsIgnoreCase("q")) {
                    System.out.println("--------");
                    System.out.println("-------- Has salido de la paginación.");
                    System.out.println("--------");
                    break;
                }
            }

            pagina++;

        } while (!libros.isLast());
    }

    private void imprimeLibro(Libro libro) {
        String info = "\n" +
                "Titulo: " + libro.getTitulo() +  "\n" +
                "Autor(es): " + imprimeAutores(libro.getAutores()) + "\n" +
                "Idioma(s): " + libro.getIdiomas() + "\n" +
                "Total de descargas: " + libro.getDescargas() + "\n";
        System.out.printf((info) + "%n");
    }

    private String imprimeAutores(List<Persona> autores) {
        if (autores == null || autores.isEmpty()) {
            return "";
        }

        if (autores.size() == 1) {
            return autores.getFirst().getNombre();
        }

        StringBuilder nombre = new StringBuilder("[ ");
        for (int i = 0; i < autores.size(); i++) {
            nombre.append(autores.get(i).getNombre());
            if (i < autores.size() - 1) {
                nombre.append(" <--> ");
            }
        }
        nombre.append(" ]");

        return nombre.toString();
    }

    private void imprimeAutor(Persona autor) {
        String info = "\n" +
                "Autor: " + autor.getNombre() +  "\n" +
                "Fecha de nacimiento: " + autor.getAnioNacimiento() + "\n" +
                "Fecha de fallecimiento: " + autor.getAnioFallecimiento() + "\n" +
                "Libro(s): " + imprimeLibros(autor.getLibros()) + "\n";
        System.out.printf((info) + "%n");
    }

    private String imprimeLibros(List<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return "";
        }

        if (libros.size() == 1) {
            return libros.getFirst().getTitulo();
        }

        StringBuilder titulo = new StringBuilder("[ ");
        for (int i = 0; i < libros.size(); i++) {
            titulo.append(libros.get(i).getTitulo());
            if (i < libros.size() - 1) {
                titulo.append(" <--> ");
            }
        }
        titulo.append(" ]");

        return titulo.toString();
    }

}
