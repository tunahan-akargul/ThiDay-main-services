package com.thiday.mainservices.words.service;

import com.thiday.mainservices.words.dto.CreateWordRequest;
import com.thiday.mainservices.words.model.Word;
import com.thiday.mainservices.words.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public String create(String ownerId, CreateWordRequest request) {
        String text = request.getText() != null ? request.getText().trim() : "";

        Word word = Word.builder()
                .text(text)
                .ownerId(ownerId)
                .createdAt(Instant.now().toString().split("T")[0])
                .build();

        Word savedWord = wordRepository.save(word);
        return savedWord.getId();
    }

    public Word getByDate(String ownerId, String dateStr) {
        return wordRepository.findByOwnerIdAndCreatedAt(ownerId, dateStr)
                .orElse(null);
    }

    public void deleteAllGlobal(){
        wordRepository.deleteAll();
    }
}