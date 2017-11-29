/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hongsong.solr;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.solr.handler.dataimport.Context;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MaYichao
 */
public class QuestionTransformerTest {
    
    public QuestionTransformerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of transformRow method, of class QuestionTransformer.
     */
    @Test
    public void testTransformRow() throws UnsupportedEncodingException {
        System.out.println("transformRow");
        //连接数据库
        Context cntxt = null;
        String expResult = "社会主义好";
        Map<String, Object> row = new HashMap<>();
        row.put("content", expResult.getBytes("utf-8"));
        QuestionTransformer instance = new QuestionTransformer();
        Map<String,Object> result = (Map) instance.transformRow(row, cntxt);
        System.out.println("result is " + result);
        assertEquals(expResult, result.get("content"));    }
    
}
