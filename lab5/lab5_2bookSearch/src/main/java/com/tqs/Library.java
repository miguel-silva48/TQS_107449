package com.tqs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
 
public class Library {
	private final List<Book> store = new ArrayList<>();
 
	public void addBook(final Book book) {
		store.add(book);
	}
 
	public List<Book> findBooksByDate(final Date from, final Date to) {
		Calendar end = Calendar.getInstance();
		end.setTime(to);
		end.roll(Calendar.YEAR, 1);
 
		return store.stream().filter(book -> {
			return from.before(book.getPublished()) && end.getTime().after(book.getPublished());
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}

    public List<Book> findBooksByAuthor(final String authorName) {
        return store.stream().filter(book -> book.getAuthor().equalsIgnoreCase(authorName)).collect(Collectors.toList());
    }

	public List<Book> findBooksByTitleStartingWith(final String prefix) {
		return store.stream().filter(book -> book.getTitle().toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Book> findBooksByTitleContaining(final String substring) {
		return store.stream().filter(book -> book.getTitle().toLowerCase().contains(substring.toLowerCase())).collect(Collectors.toList());
	}	
}
