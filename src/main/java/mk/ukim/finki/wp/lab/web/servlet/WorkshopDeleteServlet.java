package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "workshop-delete-servlet", urlPatterns = "/delete")
public class WorkshopDeleteServlet extends HttpServlet {

    private final WorkshopService workshopService;
    private final RegistrationService registrationService;
    private final SpringTemplateEngine springTemplateEngine;

    public WorkshopDeleteServlet(WorkshopService workshopService,
                                 RegistrationService registrationService,
                                 SpringTemplateEngine springTemplateEngine) {
        this.workshopService = workshopService;
        this.registrationService = registrationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        System.out.println(title);

        List<Workshop> workshops;

        registrationService.deleteByTitle(title);
        workshopService.deleteByTitle(title);
        workshops = workshopService.listAll();


        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));

        springTemplateEngine.process(
                "listWorkshops",
                context,
                resp.getWriter()
        );
    }
}