package com.spring.chris_comp303_assignment1.controllers;

import com.spring.chris_comp303_assignment1.models.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Controller
public class OrderController {

    @GetMapping("/")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "index";
    }

    @PostMapping("/submit-order")
    public String submitOrder(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "index";
        }
        model.addAttribute("order", order);
        return "show-order";
    }

    @GetMapping("/api/phones")
    public ResponseEntity<?> getPhones() {
        String json = "";
        ClassPathResource cpr = new ClassPathResource("static/json/phones.json");
        try {
            InputStream inputStream = cpr.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            json = new String(bytes, StandardCharsets.UTF_8);
            return ResponseEntity.ok(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(json);
    }
}