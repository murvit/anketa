package anketa;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project questionnaire
 */

public class Anketa extends HttpServlet {

    int counter = 0;
    int yesAnswers = 0;
    int sumAge = 0;
    int windows = 0;
    int linux = 0;
    Answer answer;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstname");
        int age = Integer.parseInt(req.getParameter("age"));
        if (age < 0 || age > 150)
            age = 18;
        String isLike = req.getParameter("islike");
        if ("Yes".equals(isLike))
            yesAnswers++;
        String os = req.getParameter("os");
        if ("windows".equals(os))
            windows++;
        else if ("linux".equals(os))
            linux++;

//        answer = new Answer(firstName, age, isLike, os);
//        EntityManager em = EntityManagerFactoryImpl.getInstance().createEntityManager();
//        em.getTransaction().begin();
//        em.persist(answer);
//        em.getTransaction().commit();
//        em.close();

        counter++;

        sumAge += age;


        int averageAge = sumAge / counter;

 //       req.setAttribute("islike", isLike);
 //       req.setAttribute("os", os);
        req.setAttribute("counter", counter);
        req.setAttribute("yesanswers", yesAnswers);
        req.setAttribute("averageage", averageAge);
        req.setAttribute("windows", windows);
        req.setAttribute("linux", linux);
        int other = counter - windows - linux;
        req.setAttribute("other", other);
        req.getRequestDispatcher("aresult.jsp").forward(req, resp);

    }
}
