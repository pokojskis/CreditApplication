package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <title>Formularz Kredytowy</title>\n");
      out.write("        <style>\n");
      out.write("            label, h2 {\n");
      out.write("                padding-bottom: 10px;\n");
      out.write("                padding-left: 10px;\n");
      out.write("            }\n");
      out.write("             input {\n");
      out.write("                margin-bottom: 10px;\n");
      out.write("                margin-left: 10px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h2>Formularz kredytowy</h2>\n");
      out.write("        <form action=\"home\" method=\"post\">\n");
      out.write("            <label>Wnioskowana kwota kredytu:</label><br>\n");
      out.write("            <input type=\"number\" name=\"amountClaimed\" min=\"0\"><br>\n");
      out.write("            <label>Ilosc rat:</label><br>\n");
      out.write("            <input type=\"number\" name=\"instalmentAmount\" min=\"0\"><br>\n");
      out.write("            <label>Oprocentowanie:</label><br>\n");
      out.write("            <input type=\"number\" name=\"creditRate\" min=\"0\" step=\"0.1\"><br>\n");
      out.write("            <label>Oplata stala:</label><br>\n");
      out.write("            <input type=\"number\" name=\"standingCharge\" min=\"0\"><br>\n");
      out.write("            <label>Rodzaj rat:</label><br>\n");
      out.write("            <input type=\"radio\" name=\"instalmentKind\" value=\"Stala\">Stala<br>\n");
      out.write("            <input type=\"radio\" name=\"instalmentKind\" value=\"Malejaca\">Malejaca<br>\n");
      out.write("            <input type=\"submit\" value=\"Wyslij\">\n");
      out.write("        </form>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
