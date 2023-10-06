package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.BookDao;
import com.shelfsense.shelfsense.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDaoImp implements BookDao {


    // region GenericDAO Methods

    @Override
    public List<Book> getAll() throws SQLException {

        List<Book> allBooks = new ArrayList<>();

        String query = "SELECT BookId, Title, Author, ISBN, Genre, PublicationDate, Publisher, Edition, Quantity " +
                "FROM Books";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int bookId = rs.getInt("BookId");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String isbn = rs.getString("ISBN");
                String genre = rs.getString("Genre");
                LocalDate publicationDate = rs.getObject("PublicationDate", LocalDate.class);
                String publisher = rs.getString("Publisher");
                String edition = rs.getString("Edition");
                int quantity = rs.getInt("Quantity");

                allBooks.add(new Book(bookId, title, author, isbn, genre, publicationDate, publisher, edition, quantity));

            }
        }
        return allBooks;
    }

    @Override
    public Book getWithId(int id) throws SQLException {

        Book book = null;

        String query = "SELECT BookId, Title, Author, ISBN, Genre, PublicationDate, Publisher, Edition, Quantity " +
                "FROM Books " +
                "WHERE BookId = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int bookId = rs.getInt("BookId");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String isbn = rs.getString("ISBN");
                String genre = rs.getString("Genre");
                LocalDate publicationDate = rs.getObject("PublicationDate", LocalDate.class);
                String publisher = rs.getString("Publisher");
                String edition = rs.getString("Edition");
                int quantity = rs.getInt("Quantity");

                book = new Book(bookId, title, author, isbn, genre, publicationDate, publisher, edition, quantity);

            }
            return book;
        }
    }

    @Override
    public int insert(Book book) throws SQLException {

        String sql = "INSERT INTO Books " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getISBN());
            ps.setString(5, book.getGenre());
            ps.setObject(6, book.getPublicationDate());
            ps.setString(7, book.getPublisher());
            ps.setString(8, book.getEdition());
            ps.setInt(9, book.getQuantity());

            return ps.executeUpdate();

        }
    }

    @Override
    public int update(Book book) throws SQLException {

        String sql = "UPDATE Books SET " +
                "BookId = ?," +
                "Title = ?, " +
                "Author = ?, " +
                "ISBN = ?, " +
                "Genre = ?, " +
                "PublicationDate = ?, " +
                "Publisher = ?, " +
                "Edition = ?, " +
                "Quantity = ? " +
                "WHERE BookId = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getISBN());
            ps.setString(5, book.getGenre());
            ps.setObject(6, book.getPublicationDate());
            ps.setString(7, book.getPublisher());
            ps.setString(8, book.getEdition());
            ps.setObject(9, book.getQuantity());
            ps.setInt(10, book.getBookId());

            return ps.executeUpdate();

        }
    }

    @Override
    public int delete(Book book) throws SQLException {

        String sql = "DELETE FROM Books WHERE BookId = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, book.getBookId());

            return ps.executeUpdate();

        }
    }

    // endregion


    // region BookDAOImp Methods

    @Override
    public Set<Integer> getUsedIds() throws SQLException {

        // Set used so that the checking of whether an ID is used is efficient
        Set<Integer> usedIds = new HashSet<>();

        String query = "SELECT BookId FROM Books";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usedIds.add(rs.getInt("BookId"));
            }
        }
        return usedIds;
    }

    @Override
    public Book getWithISBN(String bookISBN) throws SQLException {

        Book book = null;

        String query = "SELECT BookId, Title, Author, ISBN, Genre, PublicationDate, Publisher, Edition, Quantity " +
                "FROM Books " +
                "WHERE ISBN = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bookISBN);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int bookId = rs.getInt("BookId");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String isbn = rs.getString("ISBN");
                String genre = rs.getString("Genre");
                LocalDate publicationDate = rs.getObject("PublicationDate", LocalDate.class);
                String publisher = rs.getString("Publisher");
                String edition = rs.getString("Edition");
                int quantity = rs.getInt("Quantity");

                book = new Book(bookId, title, author, isbn, genre, publicationDate, publisher, edition, quantity);

            }
            return book;
        }
    }

    // endregion

}
