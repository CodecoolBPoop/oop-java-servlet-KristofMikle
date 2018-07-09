package com.codecool.webshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/cart"}, loadOnStartup = 4)
public class ShoppingCartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();

        double sumOfPrices = 0;

        StringBuilder tableContent = new StringBuilder();

        for (Item item : ItemStore.shoppingCart) {
            tableContent
                    .append("<tr>")
                    .append("<td>").append(item.getName()).append("</td>")
                    .append("<td>").append(item.getPrice()).append("</td>")
                    .append("<td>")
                    .append("</tr>");
            sumOfPrices += item.getPrice();
        }

        out.println(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "  <meta charset=\"UTF-8\">\n"
                        + "  <title>Shop</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<table>"
                        + tableContent.toString()
                        + "</table>\n"
                        + (sumOfPrices == 0 ?
                        "<h3>Your Cart is Empty</h3>" :
                        "<h3>Link " + sumOfPrices +" </h3>")
                        +"<a href=\"/shop\"><button>Shop</button></a>"
                        + "</body>\n"
                        + "</html>");
    }


}