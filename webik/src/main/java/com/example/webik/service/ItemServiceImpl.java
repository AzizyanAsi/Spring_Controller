package com.example.webik.service;

import com.example.webik.models.Item;
import com.example.webik.repository.ItemRepoImpl;
import com.example.webik.repository.ItemRepository;
import com.example.webik.service.dto.ItemDTO;
import com.example.webik.service.mapper.ItemDTOMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Override
    @Transactional
    public ItemDTO create(ItemDTO item) {
        Item entity = ItemDTOMapper.mapToEntity(item);

        itemRepository.save(entity);

        return ItemDTOMapper.mapToDTO(entity).orElse(null);
    }
    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }
    @Override
    public boolean delete(Long id) {
        if (!itemRepository.existsById(id)) {
            return false;
        }

        itemRepository.deleteById(id);

        return true;
    }
    @Override
    public Optional<ItemDTO> getItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        return ItemDTOMapper.mapToDTO(item.orElse(null));
    }
    @Override
    public List<? extends ItemDTO> getAll() {
        return ItemDTOMapper.mapToDTOs(itemRepository.findAll());
    }


    @Override
    public List<? extends ItemDTO> findByName(String name) {
        Specification<Item> specification = Specification.where(null);

        return ItemDTOMapper
                .mapToDTOs(itemRepository
                        .find(name, 300));
    }
    @Override
    public List<? extends ItemDTO> findAll(String name, int offset, int limit) {
        Specification<Item> specification = Specification.where(null);

        return ItemDTOMapper
                .mapToDTOs(itemRepository
                        .findAllNames(name,PageRequest.of(1,3,Sort.by(Sort.Direction.DESC))));

    }
}
