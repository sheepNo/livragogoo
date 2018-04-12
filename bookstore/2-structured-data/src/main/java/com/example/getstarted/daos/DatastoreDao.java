/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.daos;

import com.example.getstarted.objects.Book;
import com.example.getstarted.objects.User;
import com.example.getstarted.objects.Result;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import java.util.ArrayList;
import java.util.List;

// [START example]
public class DatastoreDao implements BookDao, UserDao {

  // [START constructor]
  private Datastore datastore;
  private KeyFactory keyFactory;

  public DatastoreDao() {
    datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service
    keyFactory = datastore.newKeyFactory().setKind("Book2");      // Is used for creating keys later
  }
  // [END constructor]
  // [START entityToBook]
  public Book entityToBook(Entity entity) {
    return new Book.Builder() // Convert to Book form
        .author(entity.getString(Book.AUTHOR))
        .description(entity.getString(Book.DESCRIPTION))
        .id(entity.getKey().getId())
        .publishedDate(entity.getString(Book.PUBLISHED_DATE))
        .title(entity.getString(Book.TITLE))
        .rating(entity.getDouble(Book.RATING))
        //.bufRating(entity.getDouble(Book.BUFRATING))
        .numberVotes(entity.getDouble(Book.NBVOTES))
        .comments(entity.getString(Book.COMMENTS))
        .imageUrl(entity.getString(Book.IMAGE_URL))
        //.bufComments(entity.getString(Book.BUFCOMMENTS))
        .build();
  }
  // [END entityToBook]
  // [START create]
  @Override
  public Long createBook(Book book) {
    IncompleteKey key = keyFactory.newKey(); // Key will be assigned once written
    FullEntity<IncompleteKey> incBookEntity = Entity.newBuilder(key) // Create the Entity
        .set(Book.AUTHOR, book.getAuthor()) // Add Property ("author", book.getAuthor())
        .set(Book.DESCRIPTION, book.getDescription())
        .set(Book.PUBLISHED_DATE, book.getPublishedDate())
        .set(Book.TITLE, book.getTitle())
        .set(Book.IMAGE_URL, book.getImageUrl())
        .set(Book.RATING, 0.0)
        //.set(Book.BUFRATING, 0.0)
        .set(Book.NBVOTES, 0.0)
        .set(Book.COMMENTS, "")
        //.set(Book.BUFCOMMENTS, "")
        .build();
    Entity bookEntity = datastore.add(incBookEntity); // Save the Entity
    return bookEntity.getKey().getId(); // The ID of the Key
  }
  // [END create]
  // [START read]
  @Override
  public Book readBook(Long bookId) {
    Entity bookEntity = datastore.get(keyFactory.newKey(bookId)); // Load an Entity for Key(id)
    return entityToBook(bookEntity);
  }
  // [END read]
  // [START update]
  @Override
  public void updateBook(Book book) {
    Key key = keyFactory.newKey(book.getId());  // From a book, create a Key
    Entity entity = Entity.newBuilder(key) // Convert Book to an Entity
        .set(Book.AUTHOR, book.getAuthor())
        .set(Book.DESCRIPTION, book.getDescription())
        .set(Book.PUBLISHED_DATE, book.getPublishedDate())
        .set(Book.TITLE, book.getTitle())
        .set(Book.IMAGE_URL, book.getImageUrl())
        .set(Book.RATING, book.getRating())
        //.set(Book.BUFRATING, book.getBufRating())
        .set(Book.NBVOTES, book.getNumberVotes())
        .set(Book.COMMENTS, book.getComments())
        //.set(Book.BUFCOMMENTS, book.getBufComments())
        .build();
    datastore.update(entity); // Update the Entity
  }
  // [END update]
  // [START rate]
  @Override
  public void rateBook(Book book) {
    Key key = keyFactory.newKey(book.getId());  // From a book, create a Key
    Entity entity = Entity.newBuilder(key) // Convert Book to an Entity
        .set(Book.AUTHOR, book.getAuthor())
        .set(Book.DESCRIPTION, book.getDescription())
        .set(Book.PUBLISHED_DATE, book.getPublishedDate())
        .set(Book.TITLE, book.getTitle())
        .set(Book.IMAGE_URL, book.getImageUrl())
        .set(Book.RATING, (book.getBufRating() + book.getRating() * book.getNumberVotes()) / (book.getNumberVotes() + 1))
        // .set(Book.BUFRATING, book.getBufRating())
        .set(Book.NBVOTES, book.getNumberVotes()+1)
        .set(Book.COMMENTS, book.getComments())
        // .set(Book.BUFCOMMENTS, book.getBufComments())
        .build();
    datastore.update(entity); // Update the Entity
  }
  // [END rate]
  // [START comments]
  @Override
  public void commentsBook(Book book) {
    Key key = keyFactory.newKey(book.getId());  // From a book, create a Key
    Entity entity = Entity.newBuilder(key)         // Convert Book to an Entity
        .set(Book.AUTHOR, book.getAuthor())
        .set(Book.DESCRIPTION, book.getDescription())
        .set(Book.PUBLISHED_DATE, book.getPublishedDate())
        .set(Book.TITLE, book.getTitle())
        .set(Book.RATING, book.getRating())
        // .set(Book.BUFRATING, book.getBufRating())
        .set(Book.IMAGE_URL, book.getImageUrl())
        .set(Book.NBVOTES, book.getNumberVotes())
        .set(Book.COMMENTS, book.getBufComments() + "%µ" + book.getComments())
        // .set(Book.BUFCOMMENTS, book.getBufComments())
        .build();
    datastore.update(entity);                   // Update the Entity
  }
  // [END comments]
  // [START delete]
  @Override
  public void deleteBook(Long bookId) {
    Key key = keyFactory.newKey(bookId); // Create the Key
    datastore.delete(key); // Delete the Entity
  }
  // [END delete]
  // [START entitiesToBooks]
  public List<Book> entitiesToBooks(QueryResults<Entity> resultList) {
    List<Book> resultBooks = new ArrayList<>();
    while (resultList.hasNext()) {  // We still have data
      resultBooks.add(entityToBook(resultList.next()));// Add the Book to the List
    }
    return resultBooks;
  }
  // [END entitiesToBooks]
  // [START listbooks]
  @Override
  public Result<Book> listBooks(String startCursorString) {
    Cursor startCursor = null;
    if (startCursorString != null && !startCursorString.equals("")) {
      startCursor = Cursor.fromUrlSafe(startCursorString);    // Where we left off
    }
    Query<Entity> query = Query.newEntityQueryBuilder()// Build the Query
        .setKind("Book2") // We only care about Books
        .setLimit(50) // Only show 50 at a time
        .setStartCursor(startCursor) // Where we left off
        .setOrderBy(OrderBy.asc(Book.TITLE)) // Use default Index "title"
        .build();
    QueryResults<Entity> resultList = datastore.run(query);   // Run the query
    List<Book> resultBooks = entitiesToBooks(resultList);     // Retrieve and convert Entities
    Cursor cursor = resultList.getCursorAfter();              // Where to start next time
    if (cursor != null && resultBooks.size() == 10) {         // Are we paging? Save Cursor
      String cursorString = cursor.toUrlSafe();               // Cursors are WebSafe
      return new Result<>(resultBooks, cursorString);
    } else {
      return new Result<>(resultBooks);
    }
  }
  // [END listbooks]
  public User entityToUser(Entity entity) {
    return new User.Builder()
        .id(entity.getKey().getId())
        .userName(entity.getString(User.USERNAME))
        .password(entity.getString(User.PASSWORD))
        // TODO un/comment if not compatible with old profiles
        .myList(entity.getString(User.MYLIST))
        // .valid(entity.getLong(User.VALID))
        .build();
  }

  @Override
  public User readUser(Long userId) {
    Entity userEntity = datastore.get(keyFactory.newKey(userId)); // Load an Entity for Key(id)
    return entityToUser(userEntity);
  }

  @Override
  public Long createUser(User user) {
    IncompleteKey key = keyFactory.newKey(); // Key will be assigned once written
    FullEntity<IncompleteKey> incUserEntity = Entity.newBuilder(key) // Create the Entity
        .set(User.USERNAME, user.getUserName())
        .set(User.PASSWORD, user.getPassword())
        // TODO maybe not needed
        .set(User.MYLIST, "")
        // .set(User.VALID, user.getValid())
        .build();
    Entity userEntity = datastore.add(incUserEntity); // Save the Entity
    return userEntity.getKey().getId(); // The ID of the Key
  }

  @Override
  public boolean login(User user) {
    User realUser = readUser(user.getId());
    if (realUser.getUserName().equals(user.getUserName()) && realUser.getPassword().equals(user.getPassword())) {
        return true;
    } else {
        return false;
    }
  }

  @Override
  public void addBookToList(User user) {
    Key key = keyFactory.newKey(user.getId());  // From a book, create a Key
    Entity entity = Entity.newBuilder(key)     
        .set(User.USERNAME, user.getUserName())
        .set(User.PASSWORD, user.getPassword())
        .set(User.MYLIST, user.getMyList())
        // .set(User.VALID, user.getValid())
        .build();
    datastore.update(entity);                   // Update the Entity
  }

  public List<User> entitiesToUsers(QueryResults<Entity> resultList) {
    List<User> resultUsers = new ArrayList<>();
    while (resultList.hasNext()) {  // We still have data
      resultUsers.add(entityToUser(resultList.next()));// Add the Book to the List
    }
    return resultUsers;
  }

  @Override
  public List<User> listUsers() {
        Query<Entity> query = Query.newEntityQueryBuilder()       // Build the Query
            .setKind("Book2")
            .setOrderBy(OrderBy.asc(User.USERNAME))
            .build();
        QueryResults<Entity> resultList = datastore.run(query);   // Run the query
        List<User> resultUsers = entitiesToUsers(resultList);     // Retrieve
        return resultUsers;
    }

    @Override
    public void deleteUser(Long userId) {
      Key key = keyFactory.newKey(userId); // Create the Key
      datastore.delete(key); // Delete the Entity
    }
}
// [END example]
