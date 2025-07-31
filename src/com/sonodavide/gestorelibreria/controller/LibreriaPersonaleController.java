package com.sonodavide.gestorelibreria.controller;

import com.sonodavide.gestorelibreria.commands.CommandHandler;
import com.sonodavide.gestorelibreria.commands.ConcreteCommandHandler;
import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.model.ObservableList.ObservableElement;
import com.sonodavide.gestorelibreria.model.ObservableList.ObservableList;
import com.sonodavide.gestorelibreria.model.ReadStatus;
import com.sonodavide.gestorelibreria.model.Review;
import com.sonodavide.gestorelibreria.view.LibreriaPersonaleView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibreriaPersonaleController {
    private LibreriaPersonaleView view;
    private ObservableList<BookDto> books;
    private ObservableElement<BookDto> selectedBook;
    private final ConcreteCommandHandler commandHandler = new ConcreteCommandHandler();
    public LibreriaPersonaleController(LibreriaPersonaleView view) {
        this.view = view;
        books = new ObservableList<>(initBooksExample());
        selectedBook = new ObservableElement<>();

        view.addImportaButtonActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Funzione Importa non ancora implementata",
                    "Importa", JOptionPane.INFORMATION_MESSAGE);
        });

        view.addEsportaButtonActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Funzione Esporta non ancora implementata",
                    "Esporta", JOptionPane.INFORMATION_MESSAGE);
        });

        view.setAddButtonActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Funzione Aggiungi non ancora implementata",
                    "Aggiungi Libro", JOptionPane.INFORMATION_MESSAGE);
        });

        view.setEditButtonActionListener(e -> {
            int selectedRow = 0;
            if (selectedRow >= 0) {
                JOptionPane.showMessageDialog(view, "Modifica libro: ",
                        "Modifica Libro", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Seleziona un libro da modificare",
                        "Nessun libro selezionato", JOptionPane.WARNING_MESSAGE);
            }
        });

        view.setDeleteButtonActionListener(e -> {
            int selectedRow = 0;
            if (selectedRow >= 0) {
                int result = JOptionPane.showConfirmDialog(view,
                        "Sei sicuro di voler eliminare: " + "?",
                        "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(view, "Libro eliminato (simulazione)",
                            "Eliminazione", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Seleziona un libro da eliminare",
                        "Nessun libro selezionato", JOptionPane.WARNING_MESSAGE);
            }
        });

        view.setAddButtonActionListener(e -> {
            String searchText = view.getSearchText();
            String searchType = view.getSelectedSearchTypeCombo();
            if (!searchText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "Ricerca '" + searchText + "' per " + searchType + " (non ancora implementata)",
                        "Ricerca", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.setTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //fai partire modifica
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {

                    int row = view.libraryTableGetRowAtPoint(e.getPoint());
                    if (row >= 0 && row < books.getSize()) {
                        Book selectedBook = null; //books.get(row)
                        JOptionPane.showMessageDialog(
                                view,
                                "Hai fatto doppio clic su: " + selectedBook.getTitle(),
                                "Doppio clic",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });
        view.setTableHeaderMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //ordina per quella roba
                int column = view.libraryTableGetColumnAtPoint(e.getPoint());
                String columnName = view.libraryTableGetColumnNameByIndex(column);

                JOptionPane.showMessageDialog(
                        view,
                        "Hai cliccato sulla colonna: " + columnName,
                        "Header cliccato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        // Aggiungi listener per la selezione delle righe
        view.setTableSelectionModelListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.libraryTableGetSelectedRow();
                if (selectedRow >= 0 && selectedRow < books.getSize()) {
                    selectedBook.setElement(books.getElement(selectedRow));
                    //updateBookDetailsPanel(selectedBook);
                }
            }
        });

        view.show();
    }

    /*
    Metodo per caricare dei libri di esempio intanto che non li carico da file e non ho repo veri
     */
    private List<BookDto> initBooksExample(){
        List<BookDto> books = new ArrayList<>();

        BookDto book1 = new BookDto();
        book1.setIsbn("978-1234567890");
        book1.setTitle("Il Signore degli Anelli");
        book1.setPages(1216);
        book1.setAuthors(Arrays.asList("J.R.R. Tolkien"));
        book1.setPublisher("Bompiani");
        book1.setReadStatus(ReadStatus.READ);
        book1.setGenres(Arrays.asList("Fantasy"));
        book1.setReview(new Review(5, "Epico e coinvolgente"));

        BookDto book2 = new BookDto();
        book2.setIsbn("978-0451524935");
        book2.setTitle("1984");
        book2.setPages(328);
        book2.setAuthors(Arrays.asList("George Orwell"));
        book2.setPublisher("Mondadori");
        book2.setReadStatus(ReadStatus.READING);
        book2.setGenres(Arrays.asList("Distopia"));
        book2.setReview(new Review(4, "Molto attuale e inquietante"));

        BookDto book3 = new BookDto();
        book3.setIsbn("978-8804681963");
        book3.setTitle("Il nome della rosa");
        book3.setPages(512);
        book3.setAuthors(Arrays.asList("Umberto Eco"));
        book3.setPublisher("Bompiani");
        book3.setReadStatus(ReadStatus.NOT_READ);
        book3.setGenres(Arrays.asList("Giallo storico"));
        book3.setReview(new Review(0, "")); // Nessuna recensione ancora

        books.add(book1);
        books.add(book2);
        books.add(book3);
        for(BookDto b : books){
            view.addBookToTable(b);
        }
        return books;
    }
}
