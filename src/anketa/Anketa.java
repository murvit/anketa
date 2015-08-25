package anketa;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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

        req.setAttribute("answer", answer);
        req.setAttribute("os", os);
        req.setAttribute("counter", counter);
        req.setAttribute("yesanswers", yesAnswers);
        req.setAttribute("averageage", averageAge);
        req.setAttribute("windows", windows);
        req.setAttribute("linux", linux);
        int other = counter-windows-linux;
        req.setAttribute("other", other);
        req.getRequestDispatcher("aresult.jsp").forward(req, resp);

    }
}
