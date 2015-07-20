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
 *
 */

public class Zipper extends HttpServlet {

    String filename;
    int start;
    int lengthSeparator;

    public void parseFile(String data) {

        start = data.indexOf("\n\r") + 3;
        lengthSeparator = data.indexOf("Content-Disposition") + 4;
        int startFilename = data.indexOf("filename=") + 10;
        int endFilename = data.indexOf("\"", startFilename);
        filename = data.substring(startFilename, endFilename);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletInputStream sis = req.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(sis);

        byte[] allData = new byte[1024 * 1024 * 5];
        byte[] inputData = null;
        int i = 0;
        int counter = 0;
        while ((i = bis.read(allData, counter, 1024)) > 0) {
            counter += i;
        }
        inputData = Arrays.copyOf(allData, counter);
        allData = null;
        String all = new String(inputData);
        parseFile(all);

        byte [] cleanFile = Arrays.copyOfRange(inputData, start, inputData.length-lengthSeparator);

        bis.close();
        sis.close();


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
