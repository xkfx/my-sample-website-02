package org.sample.website.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sample.website.entity.CartItemDO;
import org.sample.website.model.CartItemModel;

import java.util.List;

@Mapper
public interface CartItemMapper {

    /**
     * 查出对应用户购物车中的所有物品信息
     * @param user_id
     * @return
     */
    List<CartItemModel> listCartItem(@Param("user_id") Long user_id);

    /**
     * 结算
     * @param user_id
     * @return
     */
    Double getCartBill(@Param("user_id") Long user_id);

    /**
     * 这是存或者删除cartItem时调用的，确认cartItem的状况，
     * 根据num来决定是update数量还是创建或者删除cartItem
     * @param user_id
     * @param item_id
     * @return
     */
    CartItemDO getCartItem(@Param("user_id") Long user_id,
                           @Param("item_id") Long item_id);

    void saveCartItem(CartItemDO cartItemDO);

    /**
     * 返回值是测试时用的，代表所影响的条数，正常删除就是1
     * @param cartItem_id
     * @return
     */
    Integer removeCartItem(@Param("id") Long cartItem_id);

    /**
     * 更新数量，如果数量是0就直接调用rm方法而不用这个方法
     * @param cartItem_id
     * @param num
     */
    Integer updateCartItem(@Param("cartItem_id") Long cartItem_id,
                        @Param("num") Integer num);
}
