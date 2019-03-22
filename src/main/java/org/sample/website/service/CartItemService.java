package org.sample.website.service;

import org.sample.website.entity.CartItemDO;
import org.sample.website.model.CartItemModel;

import java.util.List;

public interface CartItemService {

    List<CartItemModel> listCartItem(Long uid);

    Double getBill(Long uid);

    CartItemDO saveCartItem(Long uid, Long itemId, Integer num);

    Integer removeCartItem(Long uid, Long itemId, Integer num);
}
