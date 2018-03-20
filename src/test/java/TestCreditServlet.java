//import org.junit.Test;
//import org.mockito.Mockito;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class TestCreditServlet extends Mockito {
//
//    @Test
//    public void servlet_should_not_allow_to_display_credit_schedule_if_the_claimed_amount_is_empty() throws IOException {
//
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        PrintWriter writer = mock(PrintWriter.class);
//        when(response.getWriter()).thenReturn(writer);
//
//        CreditServlet servlet = new CreditServlet();
//
//        when(request.getParameter("amountClaimed")).thenReturn("");
//        when(request.getParameter("instalmentAmount")).thenReturn("144");
//        when(request.getParameter("creditRate")).thenReturn("3");
//        when(request.getParameter("standingCharge")).thenReturn("0");
//        when(request.getParameter("instalmentKind")).thenReturn("Stala");
//
//        servlet.doPost(request, response);
//
//        verify(response).sendRedirect("/");
//    }
//}
