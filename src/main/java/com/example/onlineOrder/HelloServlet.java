package com.example.onlineOrder;

import com.example.onlineOrder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();

        Customer customer= new Customer();
        customer.setEmail("sun@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("rick");
        customer.setLastName("sun");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));


//
//        String name = request.getParameter("username");
//        // Hello
//        PrintWriter out = response.getWriter();
//        JSONObject object = new JSONObject();
//        object.put("firstname", "Rick Sun");
//        object.put("lastname", "Sun");
//        object.put("age", 45);
//        object.put("email", "test@email.com");
//        out.println(object);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JSONObject object = new JSONObject(IOUtils.toString(request.getReader()));
        String name = object.getString("name");
        int age = object.getInt("age");
        String email = object.getString("email");

        // Print customer information to IDE console
        System.out.println("Email is: " + email);
        System.out.println("Name is: " + name);
        System.out.println("Age is: " + age);
        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}