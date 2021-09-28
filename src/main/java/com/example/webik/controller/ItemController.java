package com.example.webik.controller;

import com.example.webik.config.ApplicationContext;
import com.example.webik.models.Item;
import com.example.webik.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService = ApplicationContext
            .context.getBean(ItemService.class);



    @GetMapping("/{id}/{name}")
    public Optional<Item> getItem(@PathVariable Long id,
                                  @PathVariable String name) {
        Optional<Item> itemOpt = itemService.getItem(id);
        return  itemOpt;
    }

    @GetMapping("/item")
    public List<? extends Item> getAll() {

        return itemService.getAll();
    }
    @GetMapping("/search")
    public List<Item> search(@RequestParam(required = false,defaultValue = "")String name){
        List<? extends Item> items = itemService.getAll();
        return  items.stream()
                .filter(i->i.getName().contains(name))
                .collect(Collectors.toList());
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
