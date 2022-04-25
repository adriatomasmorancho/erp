package cat.copernic.erpInsCavallBernat;


import cat.copernic.erpInsCavallBernat.model.Categoria;
import cat.copernic.erpInsCavallBernat.serveis.CategoriaServiceInterface;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rpuig
 */
@SpringBootTest
public class CategoriaTest {
    @Autowired
    private CategoriaServiceInterface categoriaService;
    
    Categoria testCategoria;
    
    @BeforeEach
    void info(){
        testCategoria = new Categoria();
        testCategoria.setNom("TESTCATEGORIA");
    }
    
    @Test
    public void afegirCategoria(){
        categoriaService.crearCategoria(testCategoria);
        assertEquals("TESTCATEGORIA", categoriaService.findByName("TESTCATEGORIA").getNom());
    }
    
    @Test
    public void buscarCategoria(){
        //assertEquals("TESTCATEGORIA", categoriaService.findByName("TESTCATEGORIA").getNom());
    }
    
    @Test
    public void editarCategoria(){
        categoriaService.editarCategoria(categoriaService.cercarCategoria(categoriaService.findByName(testCategoria.getNom())), "MYCAT");
        assertEquals("MYCAT", categoriaService.findByName("MYCAT").getNom());
    }
    
    @Test
    public void eliminarCategoria(){
        categoriaService.eliminarCategoria(categoriaService.findByName("MYCAT"));
        assertEquals(null, categoriaService.findByName("MYCAT"));
    }
}
