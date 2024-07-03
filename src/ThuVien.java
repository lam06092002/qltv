

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThuVien {
    private static ThuVien instance;
    private Connection connection;

    private ThuVien() {
        // Khởi tạo kết nối đến cơ sở dữ liệu MySQL
        try {
            String url = "jdbc:mysql://localhost:3306/library_db";
            String user = "root"; // Thay đổi nếu bạn sử dụng username khác
            String password = "06092002"; // Thay đổi mật khẩu tương ứng
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database.");
            
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database: " + e.getMessage());
        }
    }

    public static synchronized ThuVien getInstance() {
        if (instance == null) {
            instance = new ThuVien();
        }
        return instance;
    }

    

    // Các phương thức khác cho CRUD operations của Book, Member, Librarian và Borrowing

    public void addBook(Book book) {
        String sql = "INSERT INTO books(isbn, title, author, genre, copies) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getmasach());
            statement.setString(2, book.getten());
            statement.setString(3, book.gettacgia());
            statement.setString(4, book.gettheloai());
            statement.setInt(5, book.getsoluong());
            statement.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public void removeBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, isbn);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book removed successfully.");
            } else {
                System.out.println("Book not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    public void updateBook(String isbn, String title, String author, String genre, int copies) {
        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        List<Object> parameters = new ArrayList<>();

        if (title != null) {
            sql.append("title = ?, ");
            parameters.add(title);
        }
        if (author != null) {
            sql.append("author = ?, ");
            parameters.add(author);
        }
        if (genre != null) {
            sql.append("genre = ?, ");
            parameters.add(genre);
        }
        if (copies != -1) {
            sql.append("copies = ?, ");
            parameters.add(copies);
        }

       
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE isbn = ?");
        parameters.add(isbn);

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> results = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genre LIKE ? OR isbn LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            statement.setString(3, likeKeyword);
            statement.setString(4, likeKeyword);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                int copies = resultSet.getInt("copies");
                results.add(new Book(isbn, title, author, genre, copies));
            }
        } catch (SQLException e) {
            System.out.println("Error searching books: " + e.getMessage());
        }
        return results;
    }

    public void addMember(Member member) {
        String sql = "INSERT INTO members(member_id, name, address) VALUES(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getMathanhvien());
            statement.setString(2, member.getTen());
            statement.setString(3, member.getDiachi());
            statement.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    public void removeMember(String memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, memberId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Member removed successfully.");
            } else {
                System.out.println("Member not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }
    

    public void updateMember(String memberId, String name, String address) {
        String sql = "UPDATE members SET name = ?, address = ? WHERE member_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, memberId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Member updated successfully.");
            } else {
                System.out.println("Member not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }
    }

    public List<Member> searchMembers(String keyword) {
        List<Member> results = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE name LIKE ? OR member_id LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String memberId = resultSet.getString("member_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                results.add(new Member(memberId, name, address));
            }
        } catch (SQLException e) {
            System.out.println("Error searching members: " + e.getMessage());
        }
        return results;
    }
    public void addLibrarian(Librarian Librarian) {
        String sql = "INSERT INTO librarians(librarian_id, name, shift) VALUES(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Librarian.getMaso());
            statement.setString(2, Librarian.getTen());
            statement.setString(3, Librarian.getCalam());
            statement.executeUpdate();
            System.out.println("Librarian added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }
    public void removeLibrarian(String librarianId) {
        String sql = "DELETE FROM librarians WHERE librarian_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, librarianId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Librarian removed successfully.");
            } else {
                System.out.println("Librarian not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }
    public void updateLibrarian(String maso, String ten, String calam) {
        String sql = "UPDATE librarians SET name = ?, shift = ? WHERE librarian_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ten);
            statement.setString(2, calam);
            statement.setString(3, maso);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Librarian updated successfully.");
            } else {
                System.out.println("librarion not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }
    }
    public List<Librarian> searchLibrarian(String keyword) {
        List<Librarian> results = new ArrayList<>();
        String sql = "SELECT * FROM librarians WHERE name LIKE ? OR librarian_id LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String maso = resultSet.getString("librarian_id");
                String ten = resultSet.getString("name");
                String calam = resultSet.getString("shift");
                results.add(new Librarian(maso, ten,calam));
            }
        } catch (SQLException e) {
            System.out.println("Error searching members: " + e.getMessage());
        }
        return results;
    }
    
    public void muonsach(String mathanhvien, String masach) {
    String checkBookAvailability = "SELECT copies FROM books WHERE isbn = ?";
    String checkMemberExists = "SELECT * FROM members WHERE member_id = ?";
    String insertBorrowing = "INSERT INTO borrowings (member_id, isbn, borrow_date, return_date) VALUES (?, ?, ?, ?)";
    String updateBookCopies = "UPDATE books SET copies = copies - 1 WHERE isbn = ?";

    try (PreparedStatement checkBookStmt = connection.prepareStatement(checkBookAvailability);
         PreparedStatement checkMemberStmt = connection.prepareStatement(checkMemberExists);
         PreparedStatement insertBorrowingStmt = connection.prepareStatement(insertBorrowing);
         PreparedStatement updateBookStmt = connection.prepareStatement(updateBookCopies)) {

        // Kiểm tra nếu sách có sẵn
        checkBookStmt.setString(1, masach);
        ResultSet bookResult = checkBookStmt.executeQuery();
        if (!bookResult.next() || bookResult.getInt("copies") <= 0) {
            System.out.println("Book is not available.");
            return;
        }

        // Kiểm tra nếu thành viên tồn tại
        checkMemberStmt.setString(1, mathanhvien);
        ResultSet memberResult = checkMemberStmt.executeQuery();
        if (!memberResult.next()) {
            System.out.println("Member does not exist.");
            return;
        }

        // Thêm bản ghi mượn sách mới
        insertBorrowingStmt.setString(1, mathanhvien);
        insertBorrowingStmt.setString(2, masach);
        insertBorrowingStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
        insertBorrowingStmt.setNull(4, java.sql.Types.DATE); // Ngày trả sách chưa xác định
        insertBorrowingStmt.executeUpdate();

        // Cập nhật số lượng sách
        updateBookStmt.setString(1, masach);
        updateBookStmt.executeUpdate();

        System.out.println("Book borrowed successfully.");
    } catch (SQLException e) {
        System.out.println("Error borrowing book: " + e.getMessage());
    }
}
    public void trasach(String mathanhvien, String masach) {
    String checkBorrowingExists = "SELECT * FROM borrowings WHERE member_id = ? AND isbn = ? AND return_date IS NULL";
    String updateBorrowing = "UPDATE borrowings SET return_date = ? WHERE member_id = ? AND isbn = ?";
    String updateBookCopies = "UPDATE books SET copies = copies + 1 WHERE isbn = ?";

    try (PreparedStatement checkBorrowingStmt = connection.prepareStatement(checkBorrowingExists);
         PreparedStatement updateBorrowingStmt = connection.prepareStatement(updateBorrowing);
         PreparedStatement updateBookStmt = connection.prepareStatement(updateBookCopies)) {

        // Kiểm tra nếu bản ghi mượn sách tồn tại và chưa trả
        checkBorrowingStmt.setString(1, mathanhvien);
        checkBorrowingStmt.setString(2, masach);
        ResultSet borrowingResult = checkBorrowingStmt.executeQuery();
        if (!borrowingResult.next()) {
            System.out.println("No borrowing record found for this member and book.");
            return;
        }

        // Cập nhật ngày trả sách
        updateBorrowingStmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        updateBorrowingStmt.setString(2, mathanhvien);
        updateBorrowingStmt.setString(3, masach);
        updateBorrowingStmt.executeUpdate();

        // Cập nhật số lượng sách
        updateBookStmt.setString(1, masach);
        updateBookStmt.executeUpdate();

        System.out.println("Book returned successfully.");
    } catch (SQLException e) {
        System.out.println("Error returning book: " + e.getMessage());
    }
    
}
    public List<Borrowing> getBorrowings() {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM borrowings";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String mathanhvien = resultSet.getString("member_id");
                String masach = resultSet.getString("isbn");
                LocalDate ngaymuon = resultSet.getDate("borrow_date").toLocalDate();
                LocalDate ngaytra = resultSet.getDate("return_date") != null ? resultSet.getDate("return_date").toLocalDate() : null;
                Borrowing borrowing = new Borrowing(masach, mathanhvien, ngaymuon, ngaytra);
                borrowings.add(borrowing);
//              borrowings.add(new Borrowing(mathanhvien, masach, ngaymuon, ngaytra));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching borrowings: " + e.getMessage());
        }
        return borrowings;
    }
  


}

