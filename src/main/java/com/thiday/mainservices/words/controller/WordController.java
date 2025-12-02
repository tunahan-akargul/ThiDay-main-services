package com.thiday.mainservices.words.controller;

import com.thiday.mainservices.words.dto.CreateWordRequest;
import com.thiday.mainservices.words.dto.CreateWordResponse;
import com.thiday.mainservices.words.model.Word;
import com.thiday.mainservices.words.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WordController {
    private final WordService wordService;

    private String getCurrentUserId() {
        return "test-user";
    }

    @PostMapping("/post-word")
    public ResponseEntity<CreateWordResponse> create(@Valid @RequestBody CreateWordRequest request) {
        String ownerId = getCurrentUserId();
        try {
            String id = wordService.create(ownerId, request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CreateWordResponse(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-word/{date}")
    public ResponseEntity<Word> getByDate(@PathVariable String date) {
        String ownerId = getCurrentUserId();
        try {
            Word word = wordService.getByDate(ownerId, date);
            if (word == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(word);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/admin/delete-all-words")
    public ResponseEntity<Void> deleteAllGlobal() {
        wordService.deleteAllGlobal();
        return ResponseEntity.noContent().build(); // 204
    }
}