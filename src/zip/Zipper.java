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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 */

public class Zipper extends HttpServlet {

    String filename;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator iterator = upload.getItemIterator(req);
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                InputStream inputStream = item.openStream();
                if (item.isFormField())
                    item.getFieldName();
                else
                    filename = item.getName();

                int lastSlash = filename.lastIndexOf("\\");
                if (lastSlash > 0)
                    filename = filename.substring(lastSlash);

                resp.setContentType("application/zip");
                resp.setHeader("Content-Disposition", "inline; filename=" + filename + ".zip;");


                ServletOutputStream servletOutputStream = resp.getOutputStream();
                ZipOutputStream zipOutputStream = new ZipOutputStream((servletOutputStream));
                zipOutputStream.putNextEntry(new ZipEntry(filename));

                int len;
                byte[] buffer = new byte[8192];
                while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                    zipOutputStream.write(buffer, 0, len);
                }
                zipOutputStream.close();
                servletOutputStream.flush();
                servletOutputStream.close();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
