package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "workshop-list-servlet", urlPatterns = "/")
public class WorkshopListServlet extends HttpServlet {

    private final WorkshopService workshopService;
    private final SpringTemplateEngine springTemplateEngine;

    public WorkshopListServlet(WorkshopService workshopService,
                               SpringTemplateEngine springTemplateEngine) {
        this.workshopService = workshopService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String text = req.getParameter("text");
        String maxPriceParam = req.getParameter("maxPrice");

        String searchText = (text == null || text.isBlank()) ? null : text;
        Double maxPrice = null;

        if (maxPriceParam != null && !maxPriceParam.isBlank()) {
            try {
                maxPrice = Double.parseDouble(maxPriceParam);
            } catch (NumberFormatException e) {
                maxPrice = null;
            }
        }

        List<Workshop> workshops;

        if (searchText != null || maxPrice != null) {
            workshops = workshopService.search(searchText, maxPrice);
        } else {
            workshops = workshopService.listAll();
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));
        context.setVariable("searchText", searchText);
        context.setVariable("maxPrice", maxPriceParam);

        springTemplateEngine.process(
                "listWorkshops",
                context,
                resp.getWriter()
        );
    }
}