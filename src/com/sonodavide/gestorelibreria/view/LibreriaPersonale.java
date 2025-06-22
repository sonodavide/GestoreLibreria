package com.sonodavide.gestorelibreria.view;

import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.ReadStatus;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibreriaPersonale extends JFrame {
    private JTable libraryTable;
    private JTextField searchField;
    private JComboBox<String> searchTypeCombo;
    private JPanel bookDetailsPanel;
    private JButton addButton, editButton, deleteButton;

    private List<Book> books;
    // Dati di esempio
    private final Object[][] bookData = {
            {"Il Nome della Rosa", "Umberto Eco", "Storico", "Letto", "https://m.media-amazon.com/images/I/81S+VsvKnJL._AC_UF1000,1000_QL80_.jpg"},
            {"1984", "George Orwell", "Distopico", "Da leggere", "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", "In corso", "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"},
            {"Dune", "Frank Herbert", "Sci-Fi", "Letto", "https://m.media-amazon.com/images/I/81ym3QUd3KL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Nome della Rosa", "Umberto Eco", "Storico", "Letto", "https://m.media-amazon.com/images/I/81S+VsvKnJL._AC_UF1000,1000_QL80_.jpg"},
            {"1984", "George Orwell", "Distopico", "Da leggere", "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", "In corso", "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"},
            {"Dune", "Frank Herbert", "Sci-Fi", "Letto", "https://m.media-amazon.com/images/I/81ym3QUd3KL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Nome della Rosa", "Umberto Eco", "Storico", "Letto", "https://m.media-amazon.com/images/I/81S+VsvKnJL._AC_UF1000,1000_QL80_.jpg"},
            {"1984", "George Orwell", "Distopico", "Da leggere", "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", "In corso", "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"},
            {"Dune", "Frank Herbert", "Sci-Fi", "Letto", "https://m.media-amazon.com/images/I/81ym3QUd3KL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Nome della Rosa", "Umberto Eco", "Storico", "Letto", "https://m.media-amazon.com/images/I/81S+VsvKnJL._AC_UF1000,1000_QL80_.jpg"},
            {"1984", "George Orwell", "Distopico", "Da leggere", "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", "In corso", "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"},
            {"Dune", "Frank Herbert", "Sci-Fi", "Letto", "https://m.media-amazon.com/images/I/81ym3QUd3KL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Nome della Rosa", "Umberto Eco", "Storico", "Letto", "https://m.media-amazon.com/images/I/81S+VsvKnJL._AC_UF1000,1000_QL80_.jpg"},
            {"1984", "George Orwell", "Distopico", "Da leggere", "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", "In corso", "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"},
            {"Dune", "Frank Herbert", "Sci-Fi", "Letto", "https://m.media-amazon.com/images/I/81ym3QUd3KL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Nome della Rosa", "Umberto Eco", "Storico", "Letto", "https://m.media-amazon.com/images/I/81S+VsvKnJL._AC_UF1000,1000_QL80_.jpg"},
            {"1984", "George Orwell", "Distopico", "Da leggere", "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg"},
            {"Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", "In corso", "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg"},
            {"Dune", "Frank Herbert", "Sci-Fi", "Letto", "https://m.media-amazon.com/images/I/81ym3QUd3KL._AC_UF1000,1000_QL80_.jpg"},
            {"L'Alchimista", "Paulo Coelho", "Filosofico", "Letto", "https://m.media-amazon.com/images/I/71Ui-NwYUmL._AC_UF1000,1000_QL80_.jpg"}
    };

    public LibreriaPersonale() {
        setTitle("COPERTINA - La Mia Libreria Personale");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.books = this.getSampleBooks();

        // Imposta un look moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crea la barra dei menu
        createMenuBar();

        // Pannello principale con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 245));

        // Pannello superiore per pulsanti e ricerca
        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Pannello centrale con la tabella dei libri
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Pannello per i dettagli del libro selezionato
        bookDetailsPanel = createBookDetailsPanel();
        mainPanel.add(bookDetailsPanel, BorderLayout.EAST);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);


    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Opzioni
        JMenu opzioniMenu = new JMenu("Opzioni");

        JMenuItem importaItem = new JMenuItem("Importa");
        JMenuItem esportaItem = new JMenuItem("Esporta");

        // Aggiungi action listeners
        importaItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funzione Importa non ancora implementata",
                    "Importa", JOptionPane.INFORMATION_MESSAGE);
        });

        esportaItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funzione Esporta non ancora implementata",
                    "Esporta", JOptionPane.INFORMATION_MESSAGE);
        });

        opzioniMenu.add(importaItem);
        opzioniMenu.add(esportaItem);

        menuBar.add(opzioniMenu);

        setJMenuBar(menuBar);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        // Pannello superiore con i pulsanti principali
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        buttonPanel.setOpaque(false);

        // Crea i pulsanti principali
        addButton = new JButton("Aggiungi");
        editButton = new JButton("Modifica");
        deleteButton = new JButton("Elimina");

        // Stile dei pulsanti
        addButton.setBackground(new Color(40, 167, 69));
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(100, 35));

        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        editButton.setPreferredSize(new Dimension(100, 35));

        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setPreferredSize(new Dimension(100, 35));

        // Aggiungi action listeners
        addButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funzione Aggiungi non ancora implementata",
                    "Aggiungi Libro", JOptionPane.INFORMATION_MESSAGE);
        });

        editButton.addActionListener(e -> {
            int selectedRow = libraryTable.getSelectedRow();
            if (selectedRow >= 0) {
                JOptionPane.showMessageDialog(this, "Modifica libro: " + books.get(selectedRow).getTitle(),
                        "Modifica Libro", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un libro da modificare",
                        "Nessun libro selezionato", JOptionPane.WARNING_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = libraryTable.getSelectedRow();
            if (selectedRow >= 0) {
                int result = JOptionPane.showConfirmDialog(this,
                        "Sei sicuro di voler eliminare: " + books.get(selectedRow).getTitle() + "?",
                        "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, "Libro eliminato (simulazione)",
                            "Eliminazione", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un libro da eliminare",
                        "Nessun libro selezionato", JOptionPane.WARNING_MESSAGE);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Pannello di ricerca
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        searchPanel.setOpaque(false);

        searchField = new JTextField(15);
        searchField.putClientProperty("JTextField.placeholderText", "Inserisci testo di ricerca...");

        searchTypeCombo = new JComboBox<>(new String[]{"Titolo", "Autore", "Genere"});
        searchTypeCombo.setPreferredSize(new Dimension(100, 35));

        JButton searchButton = new JButton("Cerca");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(100, 35));

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            String searchType = (String) searchTypeCombo.getSelectedItem();
            if (!searchText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ricerca '" + searchText + "' per " + searchType + " (non ancora implementata)",
                        "Ricerca", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        searchPanel.add(new JLabel("Cerca:"));
        searchPanel.add(searchField);
        searchPanel.add(searchTypeCombo);
        searchPanel.add(searchButton);

        panel.add(buttonPanel, BorderLayout.WEST);
        panel.add(searchPanel, BorderLayout.EAST);

        return panel;
    }


    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // Crea i dati per la tabella
        String[] columnNames = {"Titolo", "Autore", "Genere", "Stato"};
        Object[][] tableData = new Object[books.size()][4];

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            tableData[i][0] = book.getTitle();
            tableData[i][1] = book.getAuthor();
            tableData[i][2] = book.getGenre();
            tableData[i][3] = book.getReadStatus().toString().replace("_", " ");
        }

        // Crea la tabella
        libraryTable = new JTable(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non editabile
            }
        };

        // Personalizza l'aspetto della tabella
        libraryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        libraryTable.setRowHeight(30);
        libraryTable.setFont(new Font("Arial", Font.PLAIN, 12));
        libraryTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        libraryTable.getTableHeader().setBackground(new Color(240, 240, 245));
        libraryTable.setGridColor(new Color(220, 220, 220));
        libraryTable.setShowGrid(true);
        libraryTable.setIntercellSpacing(new Dimension(1, 1));

        // Imposta la larghezza delle colonne
        libraryTable.getColumnModel().getColumn(0).setPreferredWidth(250); // Titolo
        libraryTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Autore
        libraryTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Genere
        libraryTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Stato
        libraryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = libraryTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < books.size()) {
                        Book selectedBook = books.get(row);
                        JOptionPane.showMessageDialog(
                                LibreriaPersonale.this,
                                "Hai fatto doppio clic su: " + selectedBook.getTitle(),
                                "Doppio clic",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });
        // Colora le righe alternate
        libraryTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(new Color(70, 130, 180));
                    c.setForeground(Color.WHITE);
                } else if (row % 2 == 0) {
                    c.setBackground(new Color(250, 250, 250));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });
        libraryTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = libraryTable.columnAtPoint(e.getPoint());
                String columnName = libraryTable.getColumnName(column);

                JOptionPane.showMessageDialog(
                        LibreriaPersonale.this,
                        "Hai cliccato sulla colonna: " + columnName,
                        "Header cliccato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // Aggiungi listener per la selezione delle righe
        libraryTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = libraryTable.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < books.size()) {
                    Book selectedBook = books.get(selectedRow);
                    updateBookDetailsPanel(selectedBook);
                    // Abilita i pulsanti modifica ed elimina quando un libro è selezionato
                    editButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                } else {
                    // Disabilita i pulsanti quando nessun libro è selezionato
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });

        // Inizialmente disabilita i pulsanti modifica ed elimina
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        // Crea lo scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(libraryTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tableWrapper.setBackground(Color.WHITE);
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        panel.add(tableWrapper, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBookDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(250, 0));

        panel.setBackground(Color.WHITE);

        JLabel placeholderLabel = new JLabel("Seleziona un libro per visualizzare i dettagli", JLabel.CENTER);
        placeholderLabel.setPreferredSize(new Dimension(250, 400));
        panel.add(placeholderLabel, BorderLayout.CENTER);

        return panel;
    }

    private void updateBookDetailsPanel(Book book) {
        bookDetailsPanel.removeAll();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Titolo del libro
        JLabel titleLabel = new JLabel(book.getTitle());
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Autore
        JLabel authorLabel = new JLabel("di " + book.getAuthor());
        authorLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pannello della copertina
        JPanel coverPanel = new JPanel();
        coverPanel.setOpaque(false);
        coverPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        coverPanel.setPreferredSize(new Dimension(150, 200));
        coverPanel.setMaximumSize(new Dimension(150, 200));
        coverPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        coverPanel.setBackground(new Color(240, 240, 240));
        JLabel coverLabel = new JLabel("Copertina", JLabel.CENTER);
        coverPanel.add(coverLabel);

        // Dettagli
        JPanel detailsPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        detailsPanel.setOpaque(false);
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.setMaximumSize(new Dimension(220, 50));

        detailsPanel.add(new JLabel("Genere:"));
        detailsPanel.add(new JLabel(book.getGenre()));
        detailsPanel.add(new JLabel("Stato:"));
        detailsPanel.add(new JLabel(book.getReadStatus().toString().replace("_", " ")));

        // Aggiunge tutti i componenti al pannello
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(authorLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(coverPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(detailsPanel);
        contentPanel.add(Box.createVerticalGlue()); // Spinge tutto verso l'alto

        bookDetailsPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        bookDetailsPanel.revalidate();
        bookDetailsPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibreriaPersonale app = new LibreriaPersonale();
            app.setVisible(true);
        });
    }



    public List<Book> getSampleBooks() {
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < bookData.length; i++) {
            String title = (String) bookData[i][0];
            String author = (String) bookData[i][1];
            String genre = (String) bookData[i][2];
            String readStatusStr = ((String) bookData[i][3]).toUpperCase().replace(" ", "_");
            ReadStatus readStatus;

            try {
                readStatus = ReadStatus.valueOf(readStatusStr);
            } catch (IllegalArgumentException e) {
                readStatus = ReadStatus.NOT_READ;
            }

            // Costruzione del libro
            Book book = new Book.Builder()
                    .setIsbn("ISBN-" + (1000 + i)) // placeholder ISBN
                    .setTitle(title)
                    .setAuthor(author+"★★")
                    .setPublisher("Editore Sconosciuto")
                    .setPages(300)
                    .setGenre(genre)
                    .setReadStatus(readStatus)
                    .build();

            books.add(book);
        }

        return books;
    }
}