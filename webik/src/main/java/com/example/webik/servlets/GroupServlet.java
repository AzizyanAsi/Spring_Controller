//package com.example.webik.servlets;
//
//import com.example.webik.config.ApplicationContext;
//import com.example.webik.models.Group;
//import com.example.webik.repository.GroupHibernateRepo;
//import com.example.webik.util.URLUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static com.example.webik.util.HttpConstants.CONTENT_TYPE_JSON;
//
//@WebServlet(name = "GroupServlet", urlPatterns = "/group/*")
//public class GroupServlet extends HttpServlet {
//
//    private final GroupHibernateRepo groupRepo = ApplicationContext
//            .context.getBean(GroupHibernateRepo.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType(CONTENT_TYPE_JSON);
//        Long groupId = URLUtils.getLastPathSegment(req, resp);
//        if (groupId == null) return;
//
//        Optional<Group> groupOpt = groupRepo.getGroup(groupId);
//        if (groupOpt.isPresent()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            resp.getWriter().write(objectMapper.writeValueAsString(groupOpt.get()));
//        } else {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("Resource not found: " + groupId);
//        }
//    }
//}
