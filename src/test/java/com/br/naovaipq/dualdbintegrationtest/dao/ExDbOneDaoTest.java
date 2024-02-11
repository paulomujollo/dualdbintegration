package com.br.naovaipq.dualdbintegrationtest.dao;

import com.br.naovaipq.dualdbintegrationtest.model.ExDbOneModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExDbOneDaoTest {

    @Autowired
    ExDbOneDao exDbOneDao;

    @Test
    public void deveInserirUmRegistroNaTbTwoDoDbOneComSucesso() {
        // preparacao
        ExDbOneModel exDbOneModel = new ExDbOneModel();
        exDbOneModel.setAnyway("test1");

        // acao
        ExDbOneModel dboneSaved = exDbOneDao.save(exDbOneModel);

        // verificacao
        assertNotNull(dboneSaved);
    }

    @Test
    public void deveConsultarTodosOsRegistrosDaTbOneDoDbOneComSucesso() {
        // acao
        List<ExDbOneModel> exDbOneModelList = exDbOneDao.findAll();

        // verificacao
        assertNotNull(exDbOneModelList);
        assertTrue(exDbOneModelList.size() >= 1);

    }
}
