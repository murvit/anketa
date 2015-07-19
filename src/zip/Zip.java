package zip;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * worked on it
 */
public class Zip extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream sis = req.getInputStream();
        BufferedInputStream bis = new BufferedInputStream((InputStream) sis);
        byte[] data = new byte[1024 * 1024 * 5];
        int length = bis.read(data);
        byte[] inputData = Arrays.copyOf(data, length);
        String out = new String(inputData);

  //      resp.getWriter().write(out);
 //       resp.setContentType("application");

        ZipOutputStream zout = new ZipOutputStream(resp.getOutputStream());
        zout.putNextEntry(new ZipEntry("111.txt"));
        zout.write(inputData);
        zout.closeEntry();
        zout.close();

//        String html = "<html><head><title>Test</title></head><body> %s </body></html>";
//        String getback = "<p><a href=\"anketa.html\">Get back to Questionnaire project</a></p>";
//        String getHome = "<p><a href=\"index.html\">Go Home</a></p>";
//        String result = String.format(html, out+getback+getHome);
//        resp.getWriter().write(result);
    }
}
