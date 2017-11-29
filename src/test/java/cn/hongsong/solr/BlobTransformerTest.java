/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hongsong.solr;

import com.mysql.cj.api.exceptions.ExceptionInterceptor;
import com.mysql.cj.api.log.Log;
import java.io.OutputStream;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
public class BlobTransformerTest {

    public BlobTransformerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
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

    public static Connection getConnection() {
        Connection c = null;
        try {
            c = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.10.224:3306/hongsong?serverTimezone=UTC", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("连接数据库失败.");
        }
        return c;
    }

//    /**
//     * Test of transformRow method, of class BlobTransformer.
//     */
//    @Test
//    public void testTransformRow() throws Exception {
//        System.out.println("transformRow");
//        //连接数据库
////        Connection c = getConnection();
////        Blob b = c.createBlob();
//        String expResult = "社会主义好";
//        Blob b = new com.mysql.cj.jdbc.Blob(expResult.getBytes("utf-8"), new ExceptionInterceptor() {
//            @Override
//            public ExceptionInterceptor init(Properties prprts, Log log) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void destroy() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public Exception interceptException(Exception excptn) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//
//        Map<String, Object> row = new HashMap<>();
//        row.put("content", b);
//        Context cntxt = null;
//        BlobTransformer instance = new BlobTransformer();
//        Map<String,Object> result = (Map)instance.transformRow(row, cntxt);
//        assertEquals(expResult, result.get("content"));
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    @Test
    public void testTransformRow_WithDB() throws Exception {
        System.out.println("transformRow_WithDB");
        //连接数据库
        Connection c = getConnection();
//        Blob b = c.createBlob();
        ResultSet rs = c.createStatement().executeQuery("select * from t_z_d70a42e9a77e4f4d9cd4b4b8c05f5a80_question where content is not null limit 1");
        if (!rs.next()) {
            fail("测试库中没有数据可以测试.");
        }
        Context cntxt = null;
        Map<String, Object> row = new HashMap<>();
        row.put("content", rs.getBlob("content"));
        BlobTransformer instance = new BlobTransformer();
        Map<String,Object> result = (Map)instance.transformRow(row, cntxt);
        System.out.println("result is " + result);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testTransformRow_Bytes() throws Exception {
        System.out.println("transformRow_WithDB");
        //连接数据库
        Context cntxt = null;
        String expResult = "社会主义好";
        Map<String, Object> row = new HashMap<>();
        row.put("content", expResult.getBytes("utf-8"));
        BlobTransformer instance = new BlobTransformer();
        Map<String,Object> result = (Map) instance.transformRow(row, cntxt);
        System.out.println("result is " + result);
        assertEquals(expResult, result.get("content"));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
