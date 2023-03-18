package com.weincreative.ilservice.controller;

import com.weincreative.ilservice.exception.IlAlreadyExistException;
import com.weincreative.ilservice.exception.IlNotFoundException;
import com.weincreative.ilservice.model.Il;
import com.weincreative.ilservice.service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class ILController  {
    private final IlService ilService;

    private Il getIlById(String id) {
        return ilService.getIlById(id);
    }

    @GetMapping
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
        return new ResponseEntity<>(ilService.getIller(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id){
        return new ResponseEntity<>(getIlById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il il){
        return new ResponseEntity<>(ilService.createIl(il), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIl(@PathVariable String id, @RequestBody Il il){
        ilService.updateIl(id,il);
        return new ResponseEntity<>(OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id){
        ilService.deleteId(id);
        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }

    @ExceptionHandler(IlAlreadyExistException.class)
    public ResponseEntity<String> handleIlAlreadyExistException(IlAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(),CONFLICT);
    }
}
