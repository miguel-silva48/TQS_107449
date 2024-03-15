package com.tqs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	static class BookData {
		String title;
		String author;
		String publicationDate;
	}

	@ParameterType("[0-9]{4}-[0-9]{2}-[0-9]{2}")
	public Date iso8601Date(String dateString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(dateString);
	}

	@DataTableType
	public BookData defineBookData(Map<String, String> entry) {
		BookData bookData = new BookData();
		bookData.title = entry.get("Title");
		bookData.author = entry.get("Author");
		bookData.publicationDate = entry.get("Publication Date");
		return bookData;
	}

	@Given("the following books are available:")
	public void addBooks(List<BookData> bookDataList) throws ParseException {
		for (BookData bookData : bookDataList) {
			Book book = new Book(bookData.title, bookData.author, dateFormatter.parse(bookData.publicationDate));
			library.addBook(book);
		}
	}

	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParametersDate(final Date from, final Date to) {
		result = library.findBooksByDate(from, to);
	}

	@When("the customer searches for books authored by {word}")
	public void setSearchParametersAuthor(final String authorname) {
		result = library.findBooksByAuthor(authorname);
	}

	@When("the customer searches for books starting by {word}")
	public void setSearchParametersTitlePrefix(final String title) {
		result = library.findBooksByTitleStartingWith(title);
	}

	@When("the customer searches for books that have {word} in their title")
	public void setSearchParametersTitleContained(final String part) {
		result = library.findBooksByTitleContaining(part);
	}

	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}

	@And("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
}
