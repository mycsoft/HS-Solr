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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.Transformer;
import org.bouncycastle.util.Strings;

/**
 * Blob数据转换器.
 *
 * @author MaYichao
 */
public class BlobTransformer extends Transformer {

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
//        Blob b = (Blob) row.get("content");
//        return blog2String(b, encoding);
    }

    private static String blog2String(Blob b, String encoding) {
        //Blob to String
        StringWriter writer = new StringWriter();
        InputStream in = null;
        try {
            in = b.getBinaryStream();
            int i;
            while ((i = in.read()) > 0) {
                writer.write(i);
            }

        } catch (IOException | SQLException ex) {
            LOG.error("blob to string failed!", ex);
            throw new RuntimeException("blob to string failed!", ex);
        } finally {
            try {
                writer.close();
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                LOG.error("close stream failed!", ex);
            }
        }
        try {
            String txt = new String(Strings.toByteArray(writer.toString()), encoding);

            if (LOG.isDebugEnabled()) {
                LOG.debug(txt);
            }

            return txt;
//        return content;
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

    }

}