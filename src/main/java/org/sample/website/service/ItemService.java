package org.sample.website.service;

import org.sample.website.entity.ItemDO;

import java.util.List;

public interface ItemService {

    List<ItemDO> listItem(Integer offset, Integer limit);
}
