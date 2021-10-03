package com.example.webik.service;

import com.example.webik.models.Item;
import com.example.webik.service.dto.ItemDTO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    ItemDTO create(ItemDTO item);
    Item update(Item item);
    boolean delete(Long id);
    Optional<ItemDTO> getItem(Long id);
    List<? extends ItemDTO> getAll();
    List<? extends ItemDTO> findByName(String name);
    List<? extends ItemDTO> findAll(String name,int offset, int limit);

}
