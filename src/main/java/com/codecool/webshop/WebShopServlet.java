package com.codecool.webshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WebShopServlet", urlPatterns = {"/"}, loadOnStartup = 3)
public class WebShopServlet extends HttpServlet {

    private boolean initialized = false;

    private void initItems() {
        ItemStore.listOfItems = new ArrayList<>();
        ItemStore.addItem(new Item("Book", 69.99));
        ItemStore.addItem(new Item("Game", 60));
        ItemStore.addItem(new Item("lollipop", 9));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        if (!initialized) {
            this.initItems();
            initialized = true;
        }

        StringBuilder tableContent = new StringBuilder();

        for (Item item : ItemStore.listOfItems) {
            tableContent
                    .append("<tr>")
                    .append("<td>").append(item.getName()).append("</td>")
                    .append("<td>").append(item.getPrice()).append("</td>")
                    .append("<td>").append("<form action=\"/\" method=\"post\"><button name=\"add\" value=\"").append(item.getId()).append("\" type=\"submit\">Add</button></form>").append("</td>")
                    .append("<td>").append("<form action=\"/\" method=\"post\"><button name=\"remove\" value=\"").append(item.getId()).append("\" type=\"submit\">Remove</button></form>").append("</td>")
                    .append("</tr>");
        }

        out.println("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Shop</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table>"
                + tableContent.toString()
                + "</table>\n"
                + "<a href=\"/cart\"><button>Cart</button></a>"
                + "</body>\n"
                + "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String removeId = request.getParameter("remove");
        String addId = request.getParameter("add");
        if (removeId != null) ItemStore.removeFromCart(Integer.valueOf(removeId));
        if (addId != null) ItemStore.addToCart(Integer.valueOf(addId));
        doGet(request, response);
    }
}