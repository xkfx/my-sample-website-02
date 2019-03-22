package org.sample.website.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.website.entity.CartItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartItemMapperTest {

    @Autowired
    private CartItemMapper mapper;

    @Test
    public void listCartItem() throws Exception {
        List list = mapper.listCartItem(1000L);
        System.out.println(list);
    }

    @Test
    public void getCartBill() throws Exception {
        System.out.println(mapper.getCartBill(1000L));
    }

    @Test
    public void getCartItem() throws Exception {
        System.out.println(mapper.getCartItem(1000L, 1000L));
    }

    @Test
    public void saveCartItem() throws Exception {
        CartItemDO cartItemDO = new CartItemDO(1001L, 1001L, 20);
        assertNull(cartItemDO.getId());
        mapper.saveCartItem(cartItemDO);
        assertNotNull(cartItemDO.getId());
    }

    @Test
    public void removeCartItem() throws Exception {
        CartItemDO cartItemDO = new CartItemDO(1001L, 1001L, 20);
        mapper.saveCartItem(cartItemDO);
        assertNotNull(cartItemDO.getId());
        Integer rowsAffected = mapper.removeCartItem(cartItemDO.getId());
        assertTrue(rowsAffected == 1);
    }

    @Test
    public void updateCartItem() throws Exception {
        Integer rowsAffected = mapper.updateCartItem(1000L, 20);
        assertTrue(rowsAffected == 1);
    }

}