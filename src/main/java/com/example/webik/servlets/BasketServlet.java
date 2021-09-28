package com.example.webik.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.webik.config.ApplicationContext;
import com.example.webik.models.Basket;
import com.example.webik.repository.BasketRepo;
import com.example.webik.util.URLUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.Optional;

import static com.example.webik.util.HttpConstants.CONTENT_TYPE_JSON;

@WebServlet(name = "BasketServlet", urlPatterns = "/basket/*")
public class BasketServlet extends HttpServlet {

    private final BasketRepo basketRepository = ApplicationContext
            .context.getBean(BasketRepo.class);

    /**
     * Get all baskets
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE_JSON);
        Long basketId = URLUtils.getLastPathSegment(req, resp);
        if (basketId == null) return;

        Optional<Basket> basketOpt = basketRepository.getBasket(basketId.longValue());
        if (basketOpt.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(basketOpt.get()));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Resource not found: " + basketId);
        }
    }

}
