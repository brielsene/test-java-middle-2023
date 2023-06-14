package br.com.testjavamiddle2023.controller;

import org.springframework.http.HttpStatus;
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
    @PostMapping("/first")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<String> challenge1(@RequestBody String[] object) {

        List<String> converted = new ArrayList<String>();

        for(String objects : object) {
            converted.add(objects);
        }
        Collections.reverse(converted);

        return converted;

    }
    @PostMapping("/second")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String[] challenge2(@RequestBody String[] object) {

        List<String> converted = new ArrayList<String>();

        for(String objects : object) {
            converted.add(objects);
        }
        Collections.reverse(converted);


        return object;

    }

    @PostMapping("/third")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<String> challenge3(@RequestBody List<String> object) {

        List<String> convertida = new ArrayList<String>();

        for(String objects : object) {
            convertida.add(objects);
        }
        Collections.reverse(convertida);

        return object;

    }

    @PostMapping("/upload")
    public String[] uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));

        String[] fields = new String[0];
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            fields = getFieldsFromCSV(reader);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return fields;

    }

    public static String[] getFieldsFromCSV(BufferedReader reader) throws IOException {
        List<String> fieldValues = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            for (String field : fields) {
                field = field.replaceAll("[^a-zA-Z0-9]", "");
                fieldValues.add(field);
            }

        }
        return fieldValues.toArray(new String[0]);
}
}
