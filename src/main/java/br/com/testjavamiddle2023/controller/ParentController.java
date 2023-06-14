package br.com.testjavamiddle2023.controller;


import br.com.testjavamiddle2023.domain.child.ChildParentDTO;

import br.com.testjavamiddle2023.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParentController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ParentService parentService;

    @GetMapping("/fathers")
    public ResponseEntity<List<String>> getAllFatherNames() {
        return ResponseEntity.ok(parentService.getAllFatherNames()) ;
    }

    @GetMapping("/fathers/multiple-children")
    public ResponseEntity<List<String>> getFathersWithMultipleChildren() {
        return ResponseEntity.ok(parentService.getFathersWithMultipleChildren());
    }

    @GetMapping("/children/parents")
    public ResponseEntity<List<ChildParentDTO>> getChildrenWithParents() {
        return ResponseEntity.ok(parentService.getChildrenWithParents());
    }

    @GetMapping("/john/children-count")
    public ResponseEntity<Long> getJohnChildrenCount() {
        return ResponseEntity.ok(parentService.getJohnChildrenCount());
    }

    @GetMapping("/mary/children-count")
    public ResponseEntity<Long> getMaryChildrenCount() {
        return ResponseEntity.ok(parentService.getMaryChildrenCount());
    }
}
