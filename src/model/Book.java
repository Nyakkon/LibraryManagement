/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */

import java.io.Serializable;
public class Book implements Serializable{
    private int id;
    private String title;
    private String author;
    private int publication_year;
    private String publisher;
    private String ISBN;
    private boolean active_book;

    public Book(int id, String title, String author, int publication_year, String publisher, String ISBN, boolean active_book) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.active_book = active_book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isActive_book() {
        return active_book;
    }

    public void setActive_book(boolean active_book) {
        this.active_book = active_book;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", publication_year=" + publication_year + ", publisher=" + publisher + ", ISBN=" + ISBN + ", active_book=" + active_book + '}';
    }
    
    
}
