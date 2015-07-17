package anketa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Project questionnaire
 */

public class Anketa extends HttpServlet {

    int counter = 0;
    int yesAnswers = 0;
    int sumAge = 0;
    int windows = 0;
    int linux = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        String firstName = req.getParameter("firstname");
        int age = Integer.parseInt(req.getParameter("age"));
        String answer = req.getParameter("answer");
        String os = req.getParameter("os");
        if (age < 0 || age > 150)
            age = 18;
        sumAge += age;
        if ("yes".equals(answer))
            yesAnswers++;
        if ("windows".equals(os))
            windows++;
        else if ("linux".equals(os))
            linux++;
        int averageAge = sumAge / counter;

        String html = "<html><head><title>Test</title></head><body> %s </body></html>";

        String you = String.format("<p><h1>Hello %s!</h1></p> <p>Your results:</p><p>Like to learn java: %s</p><p>Favorite OS: %s</p><br>",
                firstName,
                answer,
                os);

        String other = String.format("<p><h1>Statistic</h1></p> <p>Total votes: %d</p> <p>Like to learn java %d people(s)</p><p>Average age: %d</p>"+
                "<p>Choose Windows: %d people</p><p>Choose Linux: %d peolple</p><p>Choose other: %d people<p><br>",
                counter,
                yesAnswers,
                averageAge,
                windows,
                linux,
                counter-windows-linux);

        String getback = "<p><a href=\"anketa.html\">Get back to Questionnaire project</a></p>";
        String getHome = "<p><a href=\"index.html\">Go Home</a></p>";
        String result = String.format(html, you + other + getback + getHome);
        PrintWriter pw = resp.getWriter();
        pw.print(result);
    }
}
