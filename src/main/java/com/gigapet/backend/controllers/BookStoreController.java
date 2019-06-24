package com.gigapet.backend.controllers;


import com.gigapet.backend.models.Author;
import com.gigapet.backend.models.Book;
import com.gigapet.backend.models.ErrorDetail;
import com.gigapet.backend.models.Wrote;
import com.gigapet.backend.services.AuthorService;
import com.gigapet.backend.services.BookService;
import io.swagger.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BookStoreController {

    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;


    @ApiOperation(value = "return all authors", response = Author.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results Per Page you want to retrieve(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting creteria in the format : property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 5) Pageable pageable, HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Author> allAuthors = authorService.findAll(pageable);
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }



    @ApiOperation(value = "return all books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results Per Page you want to retrieve(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting creteria in the format : property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 5) Pageable pageable,HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Book> allBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }




    //DELETE /data/books/{id} - deletes a book and the book author combinations - but does not delete the author records.

    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBookById(HttpServletRequest request, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PUT /data/books/{id} - updates a books info (Title, Copyright, ISBN) but does NOT have to assign authors to the books.

    @ApiOperation(value = "Updates a Book", response = void.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Book updated Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error updating book", response = ErrorDetail.class)
    })
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(HttpServletRequest request, @RequestBody Book updateBook, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Book updatebook = bookService.update(updateBook, id);

        if(updateBook == null){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //POST /data/books/authors{id} - assigns a book already in the system to an author already in the system (see how roles are handled for users)

    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> assignBook(@PathVariable long bookid, @PathVariable long authorid)
    {
        Book updateBook = bookService.findBookById(bookid);
        Author updateAuthor = authorService.findAuthorById(authorid);

        if(updateAuthor == null || updateBook == null){
            throw new EntityNotFoundException();
        }

        updateBook.getWrote().add(new Wrote(updateBook, updateAuthor));
        bookService.update(updateBook, bookid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
