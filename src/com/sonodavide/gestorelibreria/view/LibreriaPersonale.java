package com.sonodavide.gestorelibreria.view;

import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.ReadStatus;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class LibreriaPersonale extends JFrame {
    private JTable libraryTable;
    private JTextField searchField;
    private JComboBox<String> genreFilter;
    private JComboBox<String> readingStatusFilter;
    private JComboBox<String> sortBy;
    private JPanel bookDetailsPanel;

    private List<Book> books;
    // Dati di esempio
    private final Object[][] bookData = {
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

        // Pannello principale con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 245));

        // Pannello superiore per ricerca e filtri
        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Pannello centrale con la tabella dei libri
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Pannello per i dettagli del libro selezionato
        bookDetailsPanel = createBookDetailsPanel();
        mainPanel.add(bookDetailsPanel, BorderLayout.EAST);

        // Pannello inferiore con valutazioni
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        
        // Pannello di ricerca
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);

        searchField = new JTextField(20);
        searchField.putClientProperty("JTextField.placeholderText", "Cerca per titolo o autore...");

        JButton searchButton = new JButton("Cerca");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);

        searchPanel.add(new JLabel("Cerca: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Pannello filtri
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterPanel.setOpaque(false);

        genreFilter = new JComboBox<>(new String[]{"Tutti i generi", "Fantasy", "Sci-Fi", "Storico", "Distopico", "Filosofico"});
        readingStatusFilter = new JComboBox<>(new String[]{"Tutti gli stati", "Letto", "Da leggere", "In corso"});
        sortBy = new JComboBox<>(new String[]{"Ordina per", "Titolo (A-Z)", "Titolo (Z-A)", "Autore", "Genere"});

        filterPanel.add(new JLabel("Genere: "));
        filterPanel.add(genreFilter);
        filterPanel.add(new JLabel("Stato: "));
        filterPanel.add(readingStatusFilter);
        filterPanel.add(new JLabel("Ordina: "));
        filterPanel.add(sortBy);

        panel.add(searchPanel, BorderLayout.WEST);
        panel.add(filterPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createBookCard(Book book) {
        // Crea una scheda con bordo arrotondato e ombra
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setPreferredSize(new Dimension(200, 320));
        card.setMinimumSize(new Dimension(180, 300));
        card.setMaximumSize(new Dimension(220, 340));
        card.setBorder(new CompoundBorder(
                new EmptyBorder(5, 5, 5, 5), // Margine esterno
                new CompoundBorder(
                        new ShadowBorder(), // Ombra
                        new CompoundBorder(
                                new LineBorder(new Color(220, 220, 220), 1, true), // Bordo arrotondato
                                new EmptyBorder(10, 10, 10, 10) // Padding interno
                        )
                )
        ));
        card.setBackground(Color.WHITE);

        // Pannello della copertina
        JPanel coverPanel = new JPanel(new BorderLayout());
        coverPanel.setPreferredSize(new Dimension(180, 220));
        coverPanel.setBackground(new Color(245, 245, 245));
        coverPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // In una vera applicazione, caricherebbe l'immagine da URL
        JLabel coverLabel = new JLabel("Copertina", JLabel.CENTER);
        coverLabel.setFont(new Font("Arial", Font.BOLD, 12));
        coverPanel.add(coverLabel, BorderLayout.CENTER);

        // Info del libro
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(book.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel(book.getAuthor());
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        authorLabel.setForeground(new Color(100, 100, 100));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Pannello per genere e stato
        JPanel metaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        metaPanel.setOpaque(false);

        JLabel genreLabel = new JLabel(book.getGenre());
        genreLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        genreLabel.setForeground(new Color(150, 150, 150));

        JLabel statusLabel = createStatusLabel("letto");

        metaPanel.add(genreLabel);
        metaPanel.add(new JLabel("•"));
        metaPanel.add(statusLabel);
        metaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Stelle di valutazione
        // JPanel starsPanel = createStarsPanel(book.getReview().getStars());
        JPanel starsPanel = createStarsPanel(5);
        starsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(2));
        infoPanel.add(authorLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(metaPanel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(starsPanel);

        // Aggiunge tutto alla scheda
        card.add(coverPanel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);

        // Aggiunge interazione alla scheda
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
                card.setBackground(new Color(250, 250, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                card.setBackground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Qui si potrebbe aprire un dialogo con i dettagli del libro
                JOptionPane.showMessageDialog(card,
                        "Hai selezionato: " + book.getTitle() + " di " + book.getAuthor(),
                        "Dettagli Libro", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return card;
    }
    private JLabel createStatusLabel(String status) {
        JLabel label = new JLabel(status);
        label.setFont(new Font("Arial", Font.BOLD, 10));

        switch (status) {
            case "Letto":
                label.setForeground(new Color(40, 167, 69));
                break;
            case "Da leggere":
                label.setForeground(new Color(23, 162, 184));
                break;
            case "In corso":
                label.setForeground(new Color(255, 193, 7));
                break;
            default:
                label.setForeground(new Color(108, 117, 125));
        }

        return label;
    }

    private JPanel createStarsPanel(int rating) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        panel.setOpaque(false);

        for (int i = 0; i < 5; i++) {
            JLabel starLabel = new JLabel(i < rating ? "★" : "☆");
            starLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            starLabel.setForeground(i < rating ? new Color(255, 193, 7) : new Color(200, 200, 200));
            panel.add(starLabel);
        }

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // Usiamo WrapLayout con allineamento a sinistra
        JPanel booksGrid = new JPanel(new WrapLayout(FlowLayout.LEFT, 10, 10));
        booksGrid.setOpaque(false);
        booksGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (Book book : books) {
            JPanel bookCard = createBookCard(book);
            bookCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateBookDetailsPanel(book);
                }
            });
            booksGrid.add(bookCard);
        }

        JScrollPane scrollPane = new JScrollPane(booksGrid);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Disabilita lo scroll orizzontale
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Mantieni il corretto ridimensionamento delle card
        booksGrid.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                booksGrid.revalidate();
                booksGrid.repaint();
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBookDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(250, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 0),
                BorderFactory.createLineBorder(new Color(220, 220, 220))
        ));
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

        // Pulsanti azione
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton editButton = new JButton("Modifica");
        JButton deleteButton = new JButton("Elimina");

        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);

        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Aggiunge tutti i componenti al pannello
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(authorLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(coverPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(detailsPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buttonsPanel);

        bookDetailsPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        bookDetailsPanel.revalidate();
        bookDetailsPanel.repaint();
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);

        // Stelle di valutazione (solo visive)
        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        starsPanel.setOpaque(false);

        // Aggiungi 5 stelle
        for (int i = 0; i < 5; i++) {
            JLabel starLabel = new JLabel("★");
            starLabel.setFont(new Font("Dialog", Font.BOLD, 24));
            starLabel.setForeground(i < 3 ? new Color(255, 215, 0) : Color.GRAY);
            starsPanel.add(starLabel);
        }

        // Bottoni per le funzionalità
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);

        JButton addButton = new JButton("Nuovo Libro");
        addButton.setBackground(new Color(40, 167, 69));
        addButton.setForeground(Color.WHITE);

        JButton exportButton = new JButton("Esporta");
        exportButton.setBackground(new Color(108, 117, 125));
        exportButton.setForeground(Color.WHITE);

        JButton infoButton = new JButton("i");
        infoButton.setBackground(new Color(23, 162, 184));
        infoButton.setForeground(Color.WHITE);
        infoButton.setFont(new Font("Dialog", Font.BOLD, 12));
        infoButton.setPreferredSize(new Dimension(30, 30));

        buttonPanel.add(addButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(infoButton);

        panel.add(starsPanel);
        panel.add(Box.createHorizontalStrut(50));
        panel.add(buttonPanel);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibreriaPersonale app = new LibreriaPersonale();
            app.setVisible(true);
        });
    }

    // Classe per creare l'ombra attorno alle schede
    private static class ShadowBorder extends AbstractBorder {
        private static final int SHADOW_SIZE = 4;

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color shadowColor = new Color(0, 0, 0, 30);
            g2.setColor(shadowColor);

            // Disegna l'ombra
            for (int i = 0; i < SHADOW_SIZE; i++) {
                int alphaLevel = 30 - 30 * i / SHADOW_SIZE;
                g2.setColor(new Color(0, 0, 0, alphaLevel));
                g2.drawRoundRect(x + i, y + i, width - i * 2, height - i * 2, 10, 10);
            }

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = SHADOW_SIZE;
            return insets;
        }
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
                    .setAuthor(author)
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