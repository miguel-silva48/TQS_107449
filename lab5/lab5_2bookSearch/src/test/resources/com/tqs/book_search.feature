@book_sample
Feature: Book Search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

    Background: Books available
      Given the following books are available:
        | Title             | Author          | Publication Date |
        | A new book        | Anonymous       | 2016-03-14       |
        | One good book     | Anonymous       | 2013-03-14       |
        | Some other book   | Tim Tomson      | 2014-08-23       |
        | How to cook a dino| Fred Flintstone | 2012-01-01       |
        | How to cook a meal| Other author    | 2018-01-01       |

    Scenario: Search books by publication year
      When the customer searches for books published between 2013-01-01 and 2014-01-01
      Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'

    Scenario: Search books by author name
      When the customer searches for books authored by Anonymous
      Then 2 books should have been found
      And Book 1 should have the title 'A new book'
      And Book 2 should have the title 'One good book'

    Scenario: Search books by title prefix
      When the customer searches for books starting by How
      Then 2 books should have been found
      And Book 1 should have the title 'How to cook a dino'
      And Book 2 should have the title 'How to cook a meal'

    Scenario: Search books by part of a title
      When the customer searches for books that have book in their title
      Then 3 books should have been found
      And Book 1 should have the title 'A new book'
      And Book 2 should have the title 'One good book'
      And Book 3 should have the title 'Some other book'

    
