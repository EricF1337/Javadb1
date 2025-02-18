import java.sql.SQLException; // Lägg till denna rad
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoanManager loanManager = new LoanManager();
        BookManager bookManager = new BookManager();

        while (true) {
            System.out.println("Välj ett alternativ:");
            System.out.println("1. Låna en bok");
            System.out.println("2. Lämna tillbaka en bok");
            System.out.println("3. Lista mina lån");
            System.out.println("4. Lista alla böcker");
            System.out.println("5. Lägg till en bok (admin)");
            System.out.println("6. Ta bort en bok (admin)");
            System.out.println("7. Avsluta");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumera newline

            switch (choice) {
                case 1:
                    System.out.print("Ange ditt användarnamn: ");
                    String userName = scanner.nextLine();
                    System.out.print("Ange bok-ID: ");
                    int bookId = scanner.nextInt();
                    try {
                        loanManager.loanBook(userName, bookId);
                    } catch (SQLException e) {
                        System.out.println("Fel vid lån av bok: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Ange låne-ID: ");
                    int loanId = scanner.nextInt();
                    try {
                        loanManager.returnBook(loanId);
                    } catch (SQLException e) {
                        System.out.println("Fel vid återlämning av bok: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Ange ditt användarnamn: ");
                    userName = scanner.nextLine();
                    try {
                        loanManager.listUserLoans(userName);
                    } catch (SQLException e) {
                        System.out.println("Fel vid listning av lån: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        bookManager.listBooks();
                    } catch (SQLException e) {
                        System.out.println("Fel vid listning av böcker: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Ange boktitel: ");
                    String title = scanner.nextLine();
                    System.out.print("Ange författare: ");
                    String author = scanner.nextLine();
                    try {
                        bookManager.addBook(title, author);
                        System.out.println("Boken har lagts till.");
                    } catch (SQLException e) {
                        System.out.println("Fel vid tillägg av bok: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.print("Ange bok-ID att ta bort: ");
                    bookId = scanner.nextInt();
                    try {
                        bookManager.removeBook(bookId);
                        System.out.println("Boken har tagits bort.");
                    } catch (SQLException e) {
                        System.out.println("Fel vid borttagning av bok: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Avslutar programmet.");
                    scanner.close();
                    return; // Avsluta programmet
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }
}
