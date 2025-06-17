package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public Item addItem(Item item) {
        return repo.save(item);
    }

    public Item getItemByCode(String code) {
        return repo.findById(code).orElse(null);
    }

    public Item updatePrice(String code, BigDecimal newPrice) {
        Item item = repo.findById(code).orElse(null);
        if (item != null) {
            item.setPrice(newPrice);
            return repo.save(item);
        }
        return null;
    }

    public Map<String, Object> generateBill(List<Item> requestedItems) {
        List<Item> billedItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Item req : requestedItems) {
            Item dbItem = repo.findById(req.getCode()).orElse(null);
            if (dbItem != null && dbItem.getQuantity() >= req.getQuantity()) {
                BigDecimal lineTotal = dbItem.getPrice().multiply(BigDecimal.valueOf(req.getQuantity()));

                Item copy = new Item();
                copy.setCode(dbItem.getCode());
                copy.setName(dbItem.getName());
                copy.setPrice(dbItem.getPrice());
                copy.setQuantity(req.getQuantity());

                billedItems.add(copy);
                total = total.add(lineTotal);
            }
        }

        Map<String, Object> bill = new HashMap<>();
        bill.put("items", billedItems);
        bill.put("total", total);
        return bill;
    }
}