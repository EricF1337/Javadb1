import java.sql.*;

public class LoanManager {
    // Låna en bok
    public void loanBook(String userName, int bookId) throws SQLException {
        String checkAvailabilitySql = "SELECT available FROM books WHERE id = ?";
        String loanSql = "INSERT INTO loans (user_name, book_id, loan_date) VALUES (?, ?, CURDATE())";
        String updateBookSql = "UPDATE books SET available = FALSE WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkAvailabilitySql);
             PreparedStatement loanStmt = conn.prepareStatement(loanSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateBookSql)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("available")) {
                loanStmt.setString(1, userName);
                loanStmt.setInt(2, bookId);
                loanStmt.executeUpdate();

                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                System.out.println("Boken har lånats ut framgångsrikt.");
            } else {
                System.out.println("Boken är inte tillgänglig.");
            }
        }
    }

    // Lämna tillbaka en bok
    public void returnBook(int loanId) throws SQLException {
        String returnSql = "UPDATE loans SET return_date = CURDATE() WHERE id = ?";
        String updateBookSql = "UPDATE books SET available = TRUE WHERE id = (SELECT book_id FROM loans WHERE id = ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement returnStmt = conn.prepareStatement(returnSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateBookSql)) {

            returnStmt.setInt(1, loanId);
            int rowsUpdated = returnStmt.executeUpdate();

            if (rowsUpdated > 0) {
                updateStmt.setInt(1, loanId);
                updateStmt.executeUpdate();
                System.out.println("Boken har lämnats tillbaka framgångsrikt.");
            } else {
                System.out.println("Ingen lånepost hittades med det angivna ID:t.");
            }
        }
    }

    // Lista nuvarande lån för en användare
    public void listUserLoans(String userName) throws SQLException {
        String sql = "SELECT l.id, b.title, l.loan_date, l.return_date " +
                     "FROM loans l JOIN books b ON l.book_id = b.id " +
                     "WHERE l.user_name = ? AND l.return_date IS NULL";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Nuvarande lån för användare: " + userName);
            while (rs.next()) {
                int loanId = rs.getInt("id");
                String bookTitle = rs.getString("title");
                Date loanDate = rs.getDate("loan_date");
                System.out.println("Låne-ID: " + loanId + ", Titel: " + bookTitle + ", Lånedatum: " + loanDate);
            }
        }
    }
}
