package br.com.testjavamiddle2023.service;

import br.com.testjavamiddle2023.domain.child.ChildParentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ParentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getAllFatherNames() {
        String sql = "SELECT DISTINCT p.Name FROM Child c "+
                "INNER JOIN Parent p "+
                "WHERE p.id = c.father_id";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> getFathersWithMultipleChildren() {
        String sql = "SELECT p.Name " +
                "FROM Parent p " +
                "JOIN Child c ON c.FATHER_ID = p.Id " +
                "GROUP BY p.Name " +
                "HAVING COUNT(c.Id) > 1";
        return jdbcTemplate.queryForList(sql, String.class);
    }

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

    public Long getJohnChildrenCount() {
        String sql = "SELECT COUNT(*) FROM Child WHERE FATHER_ID = (SELECT Id FROM Parent WHERE Name = 'John')";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    public Long getMaryChildrenCount() {
        String sql = "SELECT COUNT(*) FROM Child WHERE MOTHER_ID = (SELECT Id FROM Parent WHERE Name = 'Mary')";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


}
