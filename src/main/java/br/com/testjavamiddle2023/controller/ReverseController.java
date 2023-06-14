package br.com.testjavamiddle2023.controller;

import br.com.testjavamiddle2023.service.ReverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reverse")
public class ReverseController {

    @Autowired
    private ReverseService reverseService;

    @PostMapping("/first")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<String>> challenge1(@RequestBody String[] object) {
        return ResponseEntity.ok(reverseService.challenge1(object));
    }
    @PostMapping("/second")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String[]> challenge2(@RequestBody String[] object) {
        return ResponseEntity.ok(reverseService.challenge2(object));
    }

    @PostMapping("/third")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<String>> challenge3(@RequestBody List<String> object) {
        return ResponseEntity.ok(reverseService.challenge3(object));

    }

    @PostMapping("/upload")
    public ResponseEntity<String[]> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(reverseService.uploadFile(file));
    }

}
