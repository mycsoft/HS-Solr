/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hongsong.solr;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.Transformer;
import org.bouncycastle.util.Strings;

/**
 * 题目转换器.
 *
 * @author MaYichao
 */
public class QuestionTransformer extends Transformer {

    private static final Log LOG = LogFactory.getLog(BlobTransformer.class);

    private String encoding = "utf-8";

    @Override
    public Object transformRow(final Map<String, Object> row, Context cntxt) {

        byte[] bs = (byte[]) row.get("content");
        try {
            String t = new String(bs, encoding);

            row.put("content", t);
            return row;

        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
