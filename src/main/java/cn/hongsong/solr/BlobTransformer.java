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
 * Blob数据转换器. 本类暂时不可用.题目解析可以使用{@link QuestionTransformer}
 *
 * @author MaYichao
 */
public class BlobTransformer extends Transformer {

    //FIXME 本类应该是通用可配置的.现在只是固定针对所有byte[]字段,以及固定使用utf-8.
    private static final Log LOG = LogFactory.getLog(BlobTransformer.class);

    private String encoding = "utf-8";

    @Override
    public Object transformRow(final Map<String, Object> row, Context cntxt) {

        for (Map.Entry<String, Object> entry : row.entrySet()) {
            Object v = entry.getValue();
            if (v instanceof byte[]) {
                byte[] bs = (byte[]) v;
                try {
                    String t = new String(bs, encoding);

                    row.put(entry.getKey(), t);

                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }

        return row;
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
