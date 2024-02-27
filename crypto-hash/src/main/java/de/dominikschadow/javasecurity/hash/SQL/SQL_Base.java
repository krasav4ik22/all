/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ramki.servlet;
import com.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



/**
 *
 * @author ramakrishnan
 */
@WebServlet(name = "userCheck", urlPatterns = {"/userCheck"})
public class userCheck extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         out.println("<h1>SQL Injection Example</h1><br/><br/>");
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet userCheck</title>");
            out.println("</head>");
            out.println("<body>");
            String user = request.getParameter("user");


            System.out.println("MySQL Connect Example.");
            Connection conn = null;
            String url = "jdbc:mysql://192.168.2.128:3306/";
            String dbName = "test";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "";
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url + dbName, userName, password);
                System.out.println("Connected to the database");
         
                
                /* Контенация через переменную */
                Statement st = conn.createStatement();
                String query="SELECT * FROM  User where userid='"+user+"'";
                ResultSet res = st.executeQuery(query);

                /* format + %s */
                Statement st2 = conn.createStatement();
                ResultSet res2 = st2.executeQuery(String.format("SELECT * FROM  User where userid='%s' ;", user));

                /* MessageFormat.format */

            
                Statement st3 = conn.createStatement();
                ResultSet res3 = st3.executeQuery(MessageFormat.format("DROP User where ;-- userid='{0}' ;", user));

                /* formatted */
                Statement st4 = conn.createStatement();
                ResultSet res4 = st4.executeQuery(("SELECT * FROM  User where userid='{0}' ;".formatted(user)));
                

                /* Apache Commons */
                Statement st5 = conn.createStatement();
                Map<String, String> values5 = new HashMap<>();
                values5.put("animal", user);
                StringSubstitutor sub5 = new StringSubstitutor(values5);
                String result5 = sub5.replace("SELECT * FROM  User where userid='{animal}' ;");
                ResultSet res5 = st5.executeQuery(result5);

                /* Рекурсивные конструкции */
                Map<String, String> values6 = new HashMap<>();
                values6.put("b", "c");
                values6.put("ac", user);
                StringSubstitutor sub6 = new StringSubstitutor(values6);
                sub6.setEnableSubstitutionInVariables(true);
                String result6 = sub6.replace("SELECT * FROM  User where userid='${a${b}}' ;");
                ResultSet res6 = st6.executeQuery(result6);
                out.println(result6)
                
                /* replace */
                Statement st7 = conn.createStatement();
                String query7="ISELECT * FROM  User where userid='USER' ;".replace('USER', user);
                ResultSet res7 = st7.executeQuery(query7);
                

                }

                
                
                conn.close();
                System.out.println("Disconnected from database");
            } catch (Exception e) {
                e.printStackTrace();
            }
        





       
        out.println("</body>");
        out.println("</html>");

    }
    

    
        finally {            
            out.close();
    }
}
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/** 
 * Handles the HTTP <code>GET</code> method.
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
