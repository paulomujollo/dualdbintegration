package com.br.naovaipq.dualdbintegrationtest.dao;

import com.br.naovaipq.dualdbintegrationtest.model.ExDbOneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExDbOneDao implements RowMapper<ExDbOneModel> {

    @Autowired
    @Qualifier("dboneJdbcTemplate")
    JdbcTemplate dboneJdbcTemplate;

    @Override
    public ExDbOneModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExDbOneModel exDbOneModel = new ExDbOneModel();
        setValues(exDbOneModel, rs);

        return exDbOneModel;
    }


    public ExDbOneModel save(ExDbOneModel exDbOneModel) {
        String query = getInsert();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        dboneJdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, exDbOneModel.getAnyway());
                return preparedStatement;
            }
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            exDbOneModel.setId(generatedId.intValue());
        }

        return exDbOneModel;
    }

    public List<ExDbOneModel> findAll() {
        String query = getSelect();
        List<ExDbOneModel> exDbOneModelList = new ArrayList<>();

        exDbOneModelList = dboneJdbcTemplate.query(query, this);

        return exDbOneModelList;
    }

    public String getInsert() {
        return "INSERT INTO tbone (anyway) VALUES(?)";
    }

    public String getSelect() {
        return "SELECT id, anyway from tbone";
    }

    private void setValues(ExDbOneModel o, ResultSet rs) throws SQLException {
        o.setId(rs.getInt("id"));
        o.setAnyway(rs.getString("anyway"));
    }

}
