package com.example.webik.servlets;

import com.example.webik.config.ApplicationContext;
import com.example.webik.models.Generative;
import com.example.webik.models.Item;
import com.example.webik.models.Stock;
import com.example.webik.service.ItemNotFoundException;
import com.example.webik.service.ItemService;
import com.example.webik.service.Storage;
import com.example.webik.util.URLUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static com.example.webik.util.HttpConstants.CONTENT_TYPE_JSON;
//
//@WebServlet(name = "ItemsServlet", urlPatterns = "/item/*")
//public class ItemServlet extends HttpServlet {
//    public static final String PARAM_TYPE = "type";
//
//    private final ItemService itemService = ApplicationContext
//            .context.getBean(ItemService.class);
//
//
//    public enum ItemType {
//
//        STOCK, GENERATIVE
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Long itemId = URLUtils.getLastPathSegment(req, resp);
//        if (itemId == null) return;
//
//        Optional<Item> itemOpt = itemService.getItem(itemId);
//        if (itemOpt.isPresent()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            resp.getWriter().write(objectMapper.writeValueAsString(itemOpt.get()));
//        } else {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("Resource not found: " + itemId);
//        }
//    }

//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
//        ItemType itemType = ItemType.valueOf(typeParam);
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
//        Long itemId = URLUtils.getLastPathSegment(req, resp);
//        if (itemId == null) return;
//
//        item.setId(itemId);
//        if (itemService.update(item) == null) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("Resource not found: " + itemId);
//        }
//
//        resp.getWriter().write(objectMapper.writeValueAsString(item));
//
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long itemId = URLUtils.getLastPathSegment(req, resp);
//        if (itemId == null) return;
//
//        if (!itemService.delete(itemId)) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("Resource not found: " + itemId);
//        }
//    }
//}
//
//


