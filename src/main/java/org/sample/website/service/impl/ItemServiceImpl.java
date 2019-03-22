package org.sample.website.service.impl;

import org.sample.website.dao.ItemMapper;
import org.sample.website.entity.ItemDO;
import org.sample.website.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemDO> listItem(Integer offset, Integer limit) {
        return itemMapper.listItem(offset, limit);
    }
}
