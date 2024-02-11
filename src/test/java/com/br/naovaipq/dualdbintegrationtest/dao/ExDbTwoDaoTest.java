package com.br.naovaipq.dualdbintegrationtest.dao;

import com.br.naovaipq.dualdbintegrationtest.model.ExDbTwoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExDbTwoDaoTest {

    @Autowired
    ExDbTwoDao exDbTwoDao;

    @Test
    public void deveInserirUmRegistroNaTbOneDoDbTwoComSucesso() {
        // preparacao
        ExDbTwoModel exDbTwoModel = new ExDbTwoModel();
        exDbTwoModel.setAnyway("test2");

        // acao
        ExDbTwoModel dbtwoSaved = exDbTwoDao.save(exDbTwoModel);

        // verificacao
        assertNotNull(dbtwoSaved);
    }

    @Test
    public void deveConsultarTodosOsRegistrosDaTbTwoDoDbTwoComSucesso() {
        // acao
        List<ExDbTwoModel> exDbTwoModelList = exDbTwoDao.findAll();

        // verificacao
        assertNotNull(exDbTwoModelList);
        assertTrue(exDbTwoModelList.size() >= 1);
    }

}
