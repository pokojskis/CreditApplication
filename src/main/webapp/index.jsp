<%@page contentType="text/html; UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Formularz Kredytowy</title>
        <style>
            label, h2 {
                padding-bottom: 10px;
                padding-left: 10px;
            }
             input {
                margin-bottom: 10px;
                margin-left: 10px;
            }
        </style>
    </head>
    <body>
        <h2>Formularz kredytowy</h2>
        <form action="home" method="post">
            <label>Wnioskowana kwota kredytu:</label><br>
            <input type="number" name="amountClaimed" min="0"><br>
            <label>Ilosc rat:</label><br>
            <input type="number" name="instalmentAmount" min="0"><br>
            <label>Oprocentowanie:</label><br>
            <input type="number" name="creditRate" min="0" step="0.1"><br>
            <label>Oplata stala:</label><br>
            <input type="number" name="standingCharge" min="0"><br>
            <label>Rodzaj rat:</label><br>
            <input type="radio" name="instalmentKind" value="Stala">Stala<br>
            <input type="radio" name="instalmentKind" value="Malejaca">Malejaca<br>
            <input type="submit" value="Wyslij">
        </form>
</body>
</html>
