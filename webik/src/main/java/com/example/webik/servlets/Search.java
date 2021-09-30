//package com.example.webik.servlets;
//
//import com.example.webik.config.ApplicationContext;
//import com.example.webik.models.Item;
//import com.example.webik.service.ItemNotFoundException;
//import com.example.webik.service.ItemService;
//import com.example.webik.service.Storage;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.function.Predicate;
//
//import static com.example.webik.util.HttpConstants.CONTENT_TYPE_JSON;
//
//@WebServlet(name = "searchServlet", value = "/item/search")
//public class Search extends HttpServlet {
//    public static final String PARAM_NAME = "name";
//    public static final String PARAM_PRICE_FROM = "priceF";
//    public static final String PARAM_PRICE_TO = "priceT";
//
//
//    private final ItemService itemService = ApplicationContext
//            .context.getBean(ItemService.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType(CONTENT_TYPE_JSON);
//
//        Predicate<Item> searchPredicate = item -> true;
//        Enumeration<String> params = req.getParameterNames();
//        while (params.hasMoreElements()) {
//            String key = params.nextElement();
//            String value = req.getParameter(key);
//            if (value != null && !value.isEmpty()) {
//                Predicate<Item> predicate = getPredicateForParam(key, value);
//                searchPredicate = searchPredicate.and(predicate);
//            }
//        }
//
//        // FIXED
//        List<? extends Item> items = itemService.getAll();
//        if (items.isEmpty()) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        resp.getWriter().write(objectMapper.writeValueAsString(items));
//        } else {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("Resource not found: " );
//        }
//    }
//
//    private Predicate<Item> getPredicateForParam(String key, String value) {
//        switch (key) {
//            case PARAM_NAME:
//                return getNamePredicate(value);
//            case PARAM_PRICE_FROM:
//                return getGreaterThanPredicate(Double.parseDouble(value));
//            case PARAM_PRICE_TO:
//                return getLessThanPredicate(Double.parseDouble(value));
//        }
//
//        return item -> true;
//    }
//
//    private Predicate<Item> getNamePredicate(String value) {
//        return (i) -> i.getName().contains(value);
//    }
//
//    private Predicate<Item> getGreaterThanPredicate(double value) {
//        return (i) -> i.getPrice() >= value;
//    }
//
//    private Predicate<Item> getLessThanPredicate(double value) {
//        return (i) -> i.getPrice() <= value;
//    }
//}
