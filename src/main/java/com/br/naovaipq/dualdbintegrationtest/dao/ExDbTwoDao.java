package com.br.naovaipq.dualdbintegrationtest.dao;

import com.br.naovaipq.dualdbintegrationtest.model.ExDbTwoModel;
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
public class ExDbTwoDao implements RowMapper<ExDbTwoModel> {

    @Autowired
    @Qualifier("dbtwoJdbcTemplate")
    JdbcTemplate dbtwoJdbTemplate;

    @Override
    public ExDbTwoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExDbTwoModel exDbTwoModel = new ExDbTwoModel();
        setValues(exDbTwoModel, rs);
        return exDbTwoModel;
    }

    public ExDbTwoModel save(ExDbTwoModel exDbTwoModel) {
        String query = getInsert();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        dbtwoJdbTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, exDbTwoModel.getAnyway());
                return preparedStatement;
            }
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            exDbTwoModel.setId(generatedId.intValue());
        }

        return exDbTwoModel;
    }

    public List<ExDbTwoModel> findAll() {
        String query = getSelect();
        List<ExDbTwoModel> exDbTwoModelList = new ArrayList<>();

        exDbTwoModelList = dbtwoJdbTemplate.query(query, this);

        return exDbTwoModelList;

    }

    public String getInsert() {
        return "INSERT INTO tbtwo (anyway) VALUES(?)";
    }
    public String getSelect() {
        return "SELECT id, anyway FROM tbtwo";
    }

    private void setValues(ExDbTwoModel t, ResultSet rs) throws SQLException {
        t.setId(rs.getInt("id"));
        t.setAnyway(rs.getString("anyway"));
    }

}
