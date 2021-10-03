package com.example.webik.controller;

import com.example.webik.models.Item;
import com.example.webik.service.ItemService;
import com.example.webik.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        return ResponseEntity.of(itemService.getItem(id));
    }

    @GetMapping
    public List<? extends ItemDTO> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/search")
    public List<ItemDTO> search(@RequestParam String name) {
        return itemService.findByName(name);
    }

    @GetMapping("/findAll")
    public Page<Item> findAll(@RequestParam String name, @RequestParam int offset, @RequestParam int limit) {
        return itemService.findAll(name, offset, limit);
    }

    @PostMapping
    public @ResponseBody
    ItemDTO create(@RequestBody @Valid ItemDTO itemDTO) {
        return itemService.create(itemDTO);
    }

    @PutMapping("/{id}")
    public Item updateItem(@RequestBody Item item,
                           @PathVariable Long id) {
        itemService.update(item);
        return item;

    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.delete(id);
    }
}
