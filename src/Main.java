import java.util.*;

// =============================
// Exception Classes
// =============================
class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
// =============================
// Book Class
// =============================
class Book {
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void checkout() throws BookNotAvailableException {
        // TODO: Throw exception if book is not available
        if (!this.isAvailable) {
            throw new BookNotAvailableException("Book is not available");
        }
        System.out.println(this.title + " has been checked out");
        this.isAvailable = false;
    }

    public void returnBook() {
        if (this.isAvailable) {
            System.out.println(title + " has not been checked out yet");
        }
        else {
            System.out.println(this.title + " has been returned");
            this.isAvailable = true;
        }
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title + " by " + author + " (" + genre + ")";
    }
}

// =============================
// LibraryCollection Class
// =============================
class LibraryCollection implements Iterable<Book> {
    private Map<String, List<Book>> genreMap;

    public LibraryCollection() {
        genreMap = new HashMap<>();
    }

    // Method to add book to list
    public void addBook(Book book) {
        String genre = book.getGenre();
        List<Book> books = genreMap.get(genre);

        if (books == null) {
            books = new ArrayList<>();
            genreMap.put(genre, books);
        }
        books.add(book);
    }

    public Iterator<Book> iterator() {
        List<Book> available = new ArrayList<>();
        for (List<Book> books : genreMap.values()) {
            for (Book book : books) {
                if (book.isAvailable()) {
                    available.add(book);
                }
            }
        }
        return available.iterator();
    }
    // Method to search for book by genre
    public Iterator<Book> getGenreIterator(String genre) {
        List<Book> books = genreMap.get(genre);
        if (books == null) {
            books = Collections.emptyList();
        }
        return books.iterator();
    }

    // Method to search for any book
    public Book searchBook(String title) throws BookNotFoundException {
        for (List<Book> books : genreMap.values()) {
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    return book;
                }
            }
        }
        throw new BookNotFoundException("Book: " + title + " was not found");
    }
    // Method to check out book with exceptions
    public boolean checkoutBook(String title) {
        try {
            Book book = searchBook(title);
            book.checkout();
            return true;
        } catch (BookNotFoundException | BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    // Method to return book with exception
    public boolean returnBook(String title) {
        try {
            Book book = searchBook(title);
            book.returnBook();
            return true;
        } catch (BookNotFoundException e) {
            System.out.println("Book not found: " + title);
            return false;
        }
    }
}

