package com.sonodavide.gestorelibreria.controller;

import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.model.ObservableList.ObservableElement;
import com.sonodavide.gestorelibreria.model.ObservableList.ObservableList;
import com.sonodavide.gestorelibreria.view.LibreriaPersonaleView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibreriaPersonaleController {
    private LibreriaPersonaleView view;
    private ObservableList<BookDto> books;
    private ObservableElement<BookDto> selectedBook;

    public LibreriaPersonaleController(LibreriaPersonaleView view) {
        this.view = view;
        books = new ObservableList<>();
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
                //int selectedRow = libraryTable.getSelectedRow();
                int selectedRow = 0;
                if (selectedRow >= 0 && selectedRow < books.getSize()) {
                    Book selectedBook = null;
                    //updateBookDetailsPanel(selectedBook);
                }
            }
        });

        view.show();
    }



}
