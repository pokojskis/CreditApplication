import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class CreditServlet extends HttpServlet {

    private List<Double> capital;
    private List<Double> interest;
    private List<Double> charge;
    private List<Double> totalInstalment;

    public CreditServlet() {
        capital = new ArrayList();
        interest = new ArrayList();
        charge = new ArrayList();
        totalInstalment = new ArrayList();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // stylizacja tabeli oraz header tabeli
        String tableStyleSheet = "<head>"
                + "<style>"
                + "table { font-family: arial, sans-serif; border-collapse: collapse; width: 100% }"
                + "td, th { border: 1px solid black; text-align: left; padding: 8px; }"
                + "tr:nth-child(even) { background-color: lightgray; }"
                + "</style>"
                + "</head>";
        String tableHeader = "<table>"
                + "<tr>"
                + "<th>Nr raty</th>"
                + "<th>Kwota kapitalu</th>"
                + "<th>Kwota odsetek</th>"
                + "<th>Oplaty stale</th>"
                + "<th>Rata</th>"
                + "</tr>";

        String amountClaimed = request.getParameter("amountClaimed");
        String instalmentAmount = request.getParameter("instalmentAmount");
        String creditRate = request.getParameter("creditRate");
        String standingCharge = request.getParameter("standingCharge");
        String instalmentKind = request.getParameter("instalmentKind");

        basicValidation(amountClaimed, response);
        basicValidation(instalmentAmount, response);
        basicValidation(creditRate, response);
        basicValidation(standingCharge, response);
        basicValidation(instalmentKind, response);

        response.setContentType("text/html");
        response.getWriter().println(tableStyleSheet);
        response.getWriter().println(tableHeader);

        if (instalmentKind.equals("Stala")) {
            calculateInstalmentFixed(amountClaimed, instalmentAmount, creditRate, standingCharge);
        }
        else {
            calculateInstalmentDecreasing(amountClaimed, instalmentAmount, creditRate, standingCharge);
        }
        displaySchedule(instalmentAmount, response);

        response.getWriter().println("</table>");
    }

    // metoda sprawdzajaca czy pola w formularzu sa wypelnione
    public void basicValidation(String request, HttpServletResponse response) throws IOException{
        if (request == null || request.equals("")) {
            response.sendRedirect("/");
        }
    }

    // metoda obliczajaca rate stala
    public void calculateInstalmentFixed(String amountClaimed, String instalmentAmount, String creditRate, String standingCharge) {

        int totalAmountOfInstalments = Integer.parseInt(instalmentAmount);
        double charge = Double.parseDouble(standingCharge);
        double rateOfCredit = Double.parseDouble(creditRate) / 100;
        double claimedAmount = Double.parseDouble(amountClaimed);
        double cashToPay = claimedAmount;
        double interest = 0;
        double capital = 0;
        double instalmentAfterCharge = 0;

        int amountOfInstalmentsPerYear = calculateAmountOfInstalmentsPerYear(instalmentAmount);

        double q = 1 + (rateOfCredit / amountOfInstalmentsPerYear);
        double instalment = claimedAmount * Math.pow(q, totalAmountOfInstalments) * (q - 1) / (Math.pow(q, totalAmountOfInstalments) - 1);

        for (int i = totalAmountOfInstalments; i > 0; i--) {

            interest = cashToPay * rateOfCredit / amountOfInstalmentsPerYear;
            capital = instalment - interest;
            cashToPay -= capital;
            instalmentAfterCharge = instalment + charge;

            capitalSchedule(capital);
            interestSchedule(interest);
            standingChargeSchedule(charge);
            instalmentPlusCharge(instalmentAfterCharge);
        }
    }

    // metoda obliczajaca rate malejaca
    public void calculateInstalmentDecreasing(String amountClaimed, String instalmentAmount, String creditRate, String standingCharge) {

        int totalAmountOfInstalments = Integer.parseInt(instalmentAmount);
        double charge = Double.parseDouble(standingCharge);
        double rateOfCredit = Double.parseDouble(creditRate) / 100;
        double claimedAmount = Double.parseDouble(amountClaimed);
        double cashToPay = claimedAmount;
        double interest = 0;
        double capital = 0;
        double instalment = 0;
        double instalmentAfterCharge = 0;

        int amountOfInstalmentsPerYear = calculateAmountOfInstalmentsPerYear(instalmentAmount);

        for (int i = totalAmountOfInstalments; i > 0; i--) {

            interest = cashToPay * rateOfCredit / amountOfInstalmentsPerYear;
            capital = claimedAmount / totalAmountOfInstalments;
            cashToPay -= capital;
            instalment = capital + interest;
            instalmentAfterCharge = instalment + charge;

            capitalSchedule(capital);
            interestSchedule(interest);
            standingChargeSchedule(charge);
            instalmentPlusCharge(instalmentAfterCharge);
        }
    }

    // metoda obliczajaca ilosc rat w roku na wypadek splat raty kredytu w okresie innym niz miesieczny
    public int calculateAmountOfInstalmentsPerYear(String instalmentAmount) {
        int instalmentAmountPerYear = 12;

        if (Integer.parseInt(instalmentAmount) < 12) {
            instalmentAmountPerYear = Integer.parseInt(instalmentAmount);
        }
        else {
            instalmentAmountPerYear = 12;
        }
        return instalmentAmountPerYear;
    }

    // zapisane kwoty czesci kapitalowej, czesci odsetkowej, oplat stalych i rat calkowitych w oddzielnych listach
    public List<Double> capitalSchedule(double instalmentCapital) {
        capital.add(instalmentCapital);
        return capital;
    }

    public List<Double> interestSchedule(double interestCapital) {
        interest.add(interestCapital);
        return interest;
    }

    public List<Double> standingChargeSchedule(double standingCharge) {
        charge.add(standingCharge);
        return charge;
    }

    public List<Double> instalmentPlusCharge(double instalmentAfterCharge) {
        totalInstalment.add(instalmentAfterCharge);
        return totalInstalment;
    }

    // wyswietlenie harmonogramu
    public void displaySchedule(String instalmentAmount, HttpServletResponse response) throws IOException {

        int amountOfInstalments = Integer.parseInt(instalmentAmount);
        int counter = 1;

        for (int i = 0; i < amountOfInstalments; i++, counter++) {
            response.getWriter().println(
                    "<tr>"
                            + "<td>"
                            + counter
                            + "</td>"
                            + "<td>"
                            + String.format("%.2f", getCapital().get(i))
                            + "</td>"
                            + "<td>"
                            + String.format("%.2f", getInterest().get(i))
                            + "</td>"
                            + "<td>"
                            + String.format("%.2f", getCharge().get(i))
                            + "</td>"
                            + "<td>"
                            + String.format("%.2f", getTotalInstalment().get(i))
                            + "</td>"
                            + "</tr>");
        }

    }

//    public void generatePDF() {
//
//        Document document = new Document();
//
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream("Harmonogram.pdf"));
//
//            document.open();
//            document.add();
//            document.close();
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Double> getCapital() {
        return capital;
    }

    public List<Double> getInterest() {
        return interest;
    }

    public List<Double> getCharge() {
        return charge;
    }

    public List<Double> getTotalInstalment() {
        return totalInstalment;
    }

}
