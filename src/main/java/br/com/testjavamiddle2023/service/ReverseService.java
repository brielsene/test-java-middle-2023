package br.com.testjavamiddle2023.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReverseService {

        public List<String> challenge1(@RequestBody String[] object) {

            List<String> converted = new ArrayList<String>();

            for(String objects : object) {
                converted.add(objects);
            }
            Collections.reverse(converted);

            return converted;

        }

        public String[] challenge2(String[] object) {

            List<String> converted = new ArrayList<String>();

            for(String objects : object) {
                converted.add(objects);
            }
            Collections.reverse(converted);


            return object;

        }


        public List<String> challenge3(List<String> object) {

            List<String> convertida = new ArrayList<String>();

            for(String objects : object) {
                convertida.add(objects);
            }
            Collections.reverse(convertida);

            return object;

        }


        public String[] uploadFile(MultipartFile file) throws IOException {

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
