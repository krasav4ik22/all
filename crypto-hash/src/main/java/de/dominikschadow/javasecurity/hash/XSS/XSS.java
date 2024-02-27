
package de.dominikschadow.javasecurity.csrf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import org.apache.commons.text.StringSubstitutor;

/**
 * CSRF secured order servlet for POST requests. Processes the order and returns the result.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 168055850789919449L;
    private static final System.Logger LOG = System.getLogger(OrderServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOG.log(System.Logger.Level.INFO, "Processing order servlet...");

        if (!CSRFTokenHandler.isValid(request)) {
            LOG.log(System.Logger.Level.INFO, "Order servlet: CSRF token is invalid");
 
            String product = request.getParameter("product");
            StringTemplateLoader stringTemplate = new StringTemplateLoader();
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setTemplateLoader();
              
            stringTemplate.putTemplate("template", "Hello " + product + "!");
              
            Template template = configuration.getTemplate("template");
            Writer writer = new StringWriter();
            template.process(null, writer);
              
            return writer.toString();
        }

        LOG.log(System.Logger.Level.INFO, "Order servlet: CSRF token is valid");

        
        int quantity;

        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException ex) {
            quantity = 0;
        }

        LOG.log(System.Logger.Level.INFO, "Ordered {0} items of product {1}", quantity, product);

        response.setContentType("text/html");



        try (PrintWriter out = response.getWriter()) {
            /* XSS */
            /* Переменная */
            str = "<p><strong>Ordered " + quantity + " of product " + product + "</strong></p>";
            out.println(str);
            /* Контенация */
            out.println("<p><strong>Ordered " + quantity + " of product " + product + "</strong></p>");
            /* format + %s */
            out.println(String.format("<p><strong>Ordered  %s  of product   %s  </strong></p>", quantity, product));
            /* MessageFormat.format */
            out.println(MessageFormat.format("A string {0}.", quantity));
            /* formatted */
            out.println("First %s, then %s".formatted(quantity, product));
            /* Apache Commons */
            Map<String, String> values = new HashMap<>();
            values.put("animal", quantity);
            values.put("target", product);
            StringSubstitutor sub = new StringSubstitutor(values);
            String result = sub.replace("The ${animal} jumped over the ${target}.");
            out.println(result)
            /* Рекурсивные конструкции */
            Map<String, String> values2 = new HashMap<>();
            values2.put("b", "c");
            values2.put("ac", product);
            StringSubstitutor sub2 = new StringSubstitutor(values2);
            sub2.setEnableSubstitutionInVariables(true);
            String result2 = sub2.replace("${a${b}}");
            out.println(result2)
          
           


        } catch (IOException ex) {
            

            StringTemplateLoader stringTemplate = new StringTemplateLoader();
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setTemplateLoader();
              
            stringTemplate.putTemplate("template", "Hello " + product + "!");
              
            Template template = configuration.getTemplate("template");
            Writer writer = new StringWriter();
            template.process(null, writer);
              
            return writer.toString();
        }
    }
}


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getAttribute("product")
                
        request.setAttribute("name", "Tom");
        request.setAttribute("age", 34);
         
        getServletContext().getRequestDispatcher("/basic.jsp").forward(request, response);
    }
}


