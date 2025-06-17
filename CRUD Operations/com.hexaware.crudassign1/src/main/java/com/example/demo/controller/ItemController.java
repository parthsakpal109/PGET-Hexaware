package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping("/api/items")
    public Item addItem(@RequestBody Item item) {
        return service.addItem(item);
    }

    @GetMapping("/items/{code}")
    public Item getItem(@PathVariable String code) {
        return service.getItemByCode(code);
    }

    @PutMapping("/items/{code}/price")
    public Item updatePrice(@PathVariable String code, @RequestParam BigDecimal price) {
        return service.updatePrice(code, price);
    }

    @PostMapping("/bill")
    public Map<String, Object> generateBill(@RequestBody List<Item> items) {
        return service.generateBill(items);
    }
}