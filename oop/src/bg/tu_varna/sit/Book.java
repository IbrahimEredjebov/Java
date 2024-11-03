package bg.tu_varna.sit;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public  class Book{
    private String id;
    private String author;
    private String title;
    private String genre;
    private String description;
    private String year;
    private String keywords;
    private String rating;



    public Book(String id, String author, String title, String genre, String description, String year, String keywords, String rating) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = keywords;
        this.rating = rating;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public Element toXmlElement(Document doc) {
        Element book = doc.createElement("book");
        book.setAttribute("id", Integer.toString(Integer.parseInt(id)));

        Element authorElement = doc.createElement("author");
        authorElement.appendChild(doc.createTextNode(author));
        book.appendChild(authorElement);

        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode(title));
        book.appendChild(titleElement);

        Element genreElement = doc.createElement("genre");
        genreElement.appendChild(doc.createTextNode(genre));
        book.appendChild(genreElement);

        Element descriptionElement = doc.createElement("description");
        descriptionElement.appendChild(doc.createTextNode(description));
        book.appendChild(descriptionElement);

        Element yearOfPublicationElement = doc.createElement("yearOfPublication");
        yearOfPublicationElement.appendChild(doc.createTextNode(year));
        book.appendChild(yearOfPublicationElement);

        Element keywordsElement = doc.createElement("keywords");
        keywordsElement.appendChild(doc.createTextNode(keywords));
        book.appendChild(keywordsElement);

        Element ratingElement = doc.createElement("rating");
        ratingElement.appendChild(doc.createTextNode(rating));
        book.appendChild(ratingElement);



        return book;
    }

}
