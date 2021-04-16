package finki.ukim.mk.biblioteka.web.rest;

import finki.ukim.mk.biblioteka.model.Book;
import finki.ukim.mk.biblioteka.model.dto.BookDto;
import finki.ukim.mk.biblioteka.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> add(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.bookService.deleteById(id);

        if(this.bookService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping ("/taken/{id}")
    public ResponseEntity markAsTaken(@PathVariable Long id){
        this.bookService.markAsTaken(id);

        return ResponseEntity.ok().build();
    }


}
