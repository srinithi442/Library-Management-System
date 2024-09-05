package jdbcprgm;

import java.sql.*;
import java.util.Scanner;

public class Library {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "SQL@10";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database.");

            int choice;
            do {
                System.out.println("\nLibrary Management System operations:");
                System.out.println("1. Add Book");
                System.out.println("2. Add Member");
                System.out.println("3. Add Transaction");
                System.out.println("4. Update Book");
                System.out.println("5. Delete Member");
                System.out.println("6. Search Book");
                System.out.println("7. Read Tables");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addBook(connection);
                        break;
                    case 2:
                        addMember(connection);
                        break;
                    case 3:
                        addTransaction(connection);
                        break;
                    case 4:
                        updateBook(connection);
                        break;
                    case 5:
                        deleteMember(connection);
                        break;
                    case 6:
                        searchBook(connection);
                        break;
                    case 7:
                        readTables(connection);
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 8);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void addBook(Connection connection) throws SQLException {
        System.out.println("Enter title: ");
        scanner.nextLine(); 
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        String sql = "INSERT INTO books (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, isbn);
            statement.setInt(4, quantity);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new book was inserted successfully.");
            }
        }
    }

    private static void addMember(Connection connection) throws SQLException {
        System.out.println("Enter name: ");
        scanner.nextLine(); 
        String name = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        String sql = "INSERT INTO members (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new member was inserted successfully.");
            }
        }
    }

    private static void addTransaction(Connection connection) throws SQLException {
        System.out.println("Enter book ID: ");
        int bookId = scanner.nextInt();
        System.out.println("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter date borrowed (YYYY-MM-DD): ");
        String dateBorrowed = scanner.nextLine();

        String sql = "INSERT INTO transactions (book_id, member_id, date_borrowed) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.setInt(2, memberId);
            statement.setString(3, dateBorrowed);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new transaction was inserted successfully.");
            }
        }
    }

    private static void updateBook(Connection connection) throws SQLException {
        System.out.println("Enter book ID: ");
        int bookId = scanner.nextInt();
        System.out.println("Enter new quantity: ");
        int quantity = scanner.nextInt();

        String sql = "UPDATE books SET quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, bookId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book quantity updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }
        }
    }

    private static void deleteMember(Connection connection) throws SQLException {
        System.out.println("Enter member ID: ");
        int memberId = scanner.nextInt();

        String sql = "DELETE FROM members WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, memberId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Member deleted successfully.");
            } else {
                System.out.println("No member found with the specified ID.");
            }
        }
    }

    private static void searchBook(Connection connection) throws SQLException {
        System.out.println("Search Book:");
        System.out.println("1. By Title");
        System.out.println("2. By Author");
        System.out.println("3. By ISBN");
        System.out.print("Choose an option: ");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); 

        String sql = "";
        String searchCriteria = "";
        switch (searchOption) {
            case 1:
                System.out.print("Enter title: ");
                searchCriteria = scanner.nextLine();
                sql = "SELECT * FROM books WHERE title LIKE ?";
                break;
            case 2:
                System.out.print("Enter author: ");
                searchCriteria = scanner.nextLine();
                sql = "SELECT * FROM books WHERE author LIKE ?";
                break;
            case 3:
                System.out.println("Enter ISBN: ");
                searchCriteria = scanner.nextLine();
                sql = "SELECT * FROM books WHERE isbn LIKE ?";
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                return;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + searchCriteria + "%");
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\nSearch Results:");
            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Quantity: " + quantity);
            }
            if (!found) {
                System.out.println("No books found matching the search criteria.");
            }
        }
    }

    private static void readTables(Connection connection) throws SQLException {
        System.out.println("\nReading Tables:");
        System.out.println("1. View Books");
        System.out.println("2. View Members");
        System.out.println("3. View Transactions");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        String sql = "";
        switch (choice) {
            case 1:
                sql = "SELECT * FROM books";
                System.out.println("\nBooks Table:");
                break;
            case 2:
                sql = "SELECT * FROM members";
                System.out.println("\nMembers Table:");
                break;
            case 3:
                sql = "SELECT * FROM transactions";
                System.out.println("\nTransactions Table:");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                return;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnName + ": " + columnValue + ", ");
                }
                System.out.println();
            }
        }
    }
}