package com.example.webik.controller;

import com.example.webik.models.Item;
import com.example.webik.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

     @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}/{name}")
    public ResponseEntity<Item> getItem(@PathVariable Long id,
                                        @PathVariable String name) {
        ResponseEntity<Item> item=ResponseEntity.of(itemService.getItem(id));

        return  item;
    }

    @GetMapping
    public List<? extends Item> getAll() {
        return itemService.getAll();
    }
    @GetMapping("/search")
    public List<? extends Item> search(@RequestParam(required = false,defaultValue = "")String name){
        List<? extends Item> items = itemService.findByName(name);
        return  items;
    }
    @PostMapping
    public  Item createItem(@RequestBody Item item){
        itemService.create(item);
        return item;
    }
    @PutMapping("/{id}")
    public  Item updateItem(@RequestBody Item item,
                            @PathVariable Long id){
        itemService.update(item);
        return item;

    }
    @DeleteMapping("/{id}")
    public  void deleteItem( @PathVariable Long id){
        itemService.delete(id);
    }
}
