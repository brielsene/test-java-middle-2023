package br.com.testjavamiddle2023.controller;


import br.com.testjavamiddle2023.domain.child.ChildParentDTO;

import org.springframework.beans.factory.annotation.Autowired;
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



    @GetMapping("/fathers")
    public List<String> getAllFatherNames() {
        String sql = "SELECT Name FROM Parent";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @GetMapping("/fathers/multiple-children")
    public List<String> getFathersWithMultipleChildren() {
        String sql = "SELECT p.Name " +
                "FROM Parent p " +
                "JOIN Child c ON c.FATHER_ID = p.Id " +
                "GROUP BY p.Name " +
                "HAVING COUNT(c.Id) > 1";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @GetMapping("/children/parents")
    public List<ChildParentDTO> getChildrenWithParents() {
        String sql = "SELECT c.Name, f.Name AS FatherName, m.Name AS MotherName " +
                "FROM Child c " +
                "LEFT JOIN Parent f ON c.FATHER_ID = f.Id " +
                "LEFT JOIN Parent m ON c.MOTHER_ID = m.Id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String childName = rs.getString("Name");
            String fatherName = rs.getString("FatherName");
            String motherName = rs.getString("MotherName");
            return new ChildParentDTO(childName, fatherName, motherName);
        });
    }

    @GetMapping("/john/children-count")
    public Long getJohnChildrenCount() {
        String sql = "SELECT COUNT(*) FROM Child WHERE FATHER_ID = (SELECT Id FROM Parent WHERE Name = 'John')";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @GetMapping("/mary/children-count")
    public Long getMaryChildrenCount() {
        String sql = "SELECT COUNT(*) FROM Child WHERE MOTHER_ID = (SELECT Id FROM Parent WHERE Name = 'Mary')";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
