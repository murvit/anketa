package zip;


import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 */

public class Zipper extends HttpServlet {

    String filename;
    byte[] cleanFile;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator iterator = upload.getItemIterator(req);
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                InputStream bis = item.openStream();
                if (item.isFormField())
                    item.getFieldName();
                else
                    filename = item.getName();
                byte[] allData = new byte[1024 * 1024 * 5];
                int i = 0;
                int counter = 0;
                while ((i = bis.read(allData, counter, 1024)) > 0) {
                    counter += i;
                }
                cleanFile = Arrays.copyOf(allData, counter);
                allData = null;
                bis.close();
            }

//          Search for / in filename
            int lastSlash = filename.lastIndexOf("\\");
            if (lastSlash>0){
                filename = filename.substring(lastSlash);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.setContentType("application/zip");
        resp.setHeader("Content-Disposition", "inline; filename=" + filename + ".zip;");
        byte[] output = new byte[1024];
        int len;
        ServletOutputStream sos = resp.getOutputStream();
        ZipOutputStream zout = new ZipOutputStream((sos));
        zout.putNextEntry(new ZipEntry(filename));
        ByteArrayInputStream bais = new ByteArrayInputStream(cleanFile);
        while ((len = bais.read(output)) != -1) {
            zout.write(output, 0, len);
        }
        bais.close();
        zout.close();
        sos.flush();
    }
}
