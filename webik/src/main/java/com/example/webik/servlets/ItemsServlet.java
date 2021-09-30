//package com.example.webik.servlets;
//
//import com.example.webik.config.ApplicationContext;
//import com.example.webik.models.Generative;
//import com.example.webik.models.Item;
//import com.example.webik.models.Stock;
//import com.example.webik.service.ItemService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import static com.example.webik.util.HttpConstants.CONTENT_TYPE_JSON;
//
//@WebServlet(name = "ItemsServlet", urlPatterns = "/item")
//public class ItemsServlet extends HttpServlet {
//
//    public static final String PARAM_TYPE = "type";
//
//    private final ItemService itemService = ApplicationContext
//            .context.getBean(ItemService.class);
//
//
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String typeParam = req.getParameter(PARAM_TYPE);
//        resp.setContentType(CONTENT_TYPE_JSON);
//        if (typeParam == null || typeParam.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("Missing param: " + PARAM_TYPE);
//            return;
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ItemServlet.ItemType itemType = ItemServlet.ItemType.valueOf(typeParam);
//
//        String payload = req.getReader().lines().collect(Collectors.joining());
//        Item item;
//        switch (itemType) {
//            case GENERATIVE:
//                item = objectMapper.readValue(payload, Generative.class);
//                break;
//            case STOCK:
//                item = objectMapper.readValue(payload, Stock.class);
//                break;
//            default:
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                resp.getWriter().write("Wrong type: " + itemType);
//                return;
//        }
//
//        itemService.create(item);
//
//        resp.getWriter().write(objectMapper.writeValueAsString(item));
//    }
//
//
//
//}