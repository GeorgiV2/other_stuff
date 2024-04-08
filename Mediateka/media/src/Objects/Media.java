package Objects;

public class Media {
    private int id;
    private String name;
    private String author;
    private String year;
    private String genre;
    private String description;
    private String barcode;
    private int quantity;

    public Media(int id, String name, String author, String year, String genre, String description, String barcode,int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
