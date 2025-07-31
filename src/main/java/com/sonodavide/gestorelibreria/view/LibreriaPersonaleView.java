package com.sonodavide.gestorelibreria.view;

import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.BookDto;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class LibreriaPersonaleView extends JFrame {
    private JTable libraryTable;
    private JTextField searchField;
    private JComboBox<String> searchTypeCombo;
    private JPanel bookDetailsPanel;
    private JButton addButton, editButton, deleteButton;
    private JButton searchButton;

    private JMenuItem importaButton;
    private JMenuItem esportaButton;

    public String getSelectedSearchTypeCombo(){
        return searchTypeCombo.getSelectedItem().toString();
    }

    public void setSearchTypeComboItems(List<String> strings){
        if(strings == null) throw new NullPointerException("Search type combo is null");
        searchTypeCombo = new JComboBox<>(strings.toArray(new String[0]));
        if (!strings.isEmpty()) {
            searchTypeCombo.setSelectedIndex(0);
        }
    }

    public LibreriaPersonaleView() {
        setTitle("COPERTINA - La Mia Libreria Personale");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    public void addImportaButtonActionListener(ActionListener actionListener) {
        importaButton.addActionListener(actionListener);
    }

    public void addEsportaButtonActionListener(ActionListener actionListener) {
        esportaButton.addActionListener(actionListener);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Opzioni
        JMenu opzioniMenu = new JMenu("Opzioni");

        importaButton = new JMenuItem("Importa");
        esportaButton = new JMenuItem("Esporta");

        opzioniMenu.add(importaButton);
        opzioniMenu.add(esportaButton);

        menuBar.add(opzioniMenu);

        setJMenuBar(menuBar);
    }

    public void setAddButtonActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void setEditButtonActionListener(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void setDeleteButtonActionListener(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }

    public String getSearchText(){
        return searchField.getText();
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

        searchButton = new JButton("Cerca");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(100, 35));

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

        // Crea i dati per la tabella - ora con "Autori" e "Generi" al plurale
        String[] columnNames = {"Titolo", "Autori", "Generi", "Stato", "Recensione"};

        // Usa DefaultTableModel invece di array statico
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non editabile
            }
        };

        // Crea la tabella con il modello
        libraryTable = new JTable(tableModel) {
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
        libraryTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Autori
        libraryTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Generi (più largo per liste)
        libraryTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Stato
        libraryTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Recensione

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

    public void setTableSelectionModelListSelectionListener(ListSelectionListener l) {
        libraryTable.getSelectionModel().addListSelectionListener(l);
    }

    public void setTableHeaderMouseListener(MouseListener l) {
        libraryTable.getTableHeader().addMouseListener(l);
    }

    public void setTableMouseListener(MouseListener l) {
        libraryTable.addMouseListener(l);
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

        // Autori (ora come lista)
        String authorsText = book.getAuthors().isEmpty() ? "Autore sconosciuto" : String.join(", ", book.getAuthors());
        JLabel authorLabel = new JLabel("di " + authorsText);
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

        // Generi (ora come lista)
        String genresText = book.getGenres().isEmpty() ? "Nessun genere" : String.join(", ", book.getGenres());
        detailsPanel.add(new JLabel("Generi:"));
        detailsPanel.add(new JLabel(genresText));
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

    // Metodo per aggiungere un nuovo libro - ora gestisce liste
    public void addBookToTable(BookDto newBook) {
        DefaultTableModel model = (DefaultTableModel) libraryTable.getModel();

        // Converte le liste in stringhe separate da virgola
        String authorsText = newBook.getAuthors().isEmpty() ? "Autore sconosciuto" : String.join(", ", newBook.getAuthors());
        String genresText = newBook.getGenres().isEmpty() ? "Nessun genere" : String.join(", ", newBook.getGenres());

        Object[] rowData = {
                newBook.getTitle(),
                authorsText,
                genresText,
                newBook.getReadStatus().getVal(),
                "✩".repeat(newBook.getReview().getStars())
        };
        model.addRow(rowData);
    }

    // Metodo per rimuovere una riga
    public void removeBookFromTable(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) libraryTable.getModel();
        model.removeRow(rowIndex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibreriaPersonaleView app = new LibreriaPersonaleView();
            app.setVisible(true);
        });
    }

    public int libraryTableGetSelectedRow() {
        return libraryTable.getSelectedRow();
    }

    public int libraryTableGetColumnAtPoint(Point p) {
        return libraryTable.columnAtPoint(p); // Fix: era ricorsivo!
    }

    public int libraryTableGetRowAtPoint(Point p){
        return libraryTable.rowAtPoint(p);
    }

    public String libraryTableGetColumnNameByIndex(int index){
        return libraryTable.getColumnName(index);
    }
}