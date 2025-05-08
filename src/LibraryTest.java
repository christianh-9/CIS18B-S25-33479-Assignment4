import java.util.Scanner;
import java.util.Iterator;

public class LibraryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryCollection library = new LibraryCollection();

        library.addBook(new Book ("The Guest List", "Lucy Foley", "Mystery"));
        library.addBook(new Book("The Ruins", "Scott Smith", "Horror"));
        library.addBook(new Book("The Thursday Murder Club", "Richard Osman", "Mystery"));

        System.out.print("Enter the genre to find available books: ");
        String getGenre = scanner.nextLine();
        Iterator<Book> genreIterator = library.getGenreIterator(getGenre);
        if (!genreIterator.hasNext()) {
            System.out.println("No available books under genre: " + getGenre);
        }
        else {
            while (genreIterator.hasNext()) {
                Book book = genreIterator.next();
                if (book.isAvailable()) {
                    System.out.println(book);
                }
            }
        }

        System.out.println();
        System.out.println("Enter a book to checkout: ");
        String getTitle = scanner.nextLine();
        library.checkoutBook(getTitle);
        System.out.println();

        System.out.println("Enter a book to return: ");
        String getReturnTitle = scanner.nextLine();
        library.returnBook(getReturnTitle);

        scanner.close();
    }
}