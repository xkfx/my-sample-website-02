package org.sample.website.service.impl;

import org.sample.website.annotation.Authentication;
import org.sample.website.dao.CartItemMapper;
import org.sample.website.entity.CartItemDO;
import org.sample.website.exception.AuthenticationException;
import org.sample.website.model.CartItemModel;
import org.sample.website.model.User;
import org.sample.website.service.CartItemService;
import org.sample.website.service.JwtUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemMapper cartItemMapper;

    private final JwtUserAuthenticationService jwtUserAuthenticationService;

    @Autowired
    public CartItemServiceImpl(CartItemMapper cartItemMapper, JwtUserAuthenticationService jwtUserAuthenticationService) {
        this.cartItemMapper = cartItemMapper;
        this.jwtUserAuthenticationService = jwtUserAuthenticationService;
    }

    @Override
    public List<CartItemModel> listCartItem(Long uid) {
        User user = jwtUserAuthenticationService.getCurrentUser();
        // user会随机为null和一个刚刚登陆过的用户值是什么问题？？？？？？？
        if (user != null && Objects.equals(user.getId(), uid)) {
            return cartItemMapper.listCartItem(uid);
        } else {
            throw new AuthenticationException("你不具备查看他人购物车的权限。");
        }
    }

    @Override
    public Double getBill(Long uid) {
        User user = jwtUserAuthenticationService.getCurrentUser();
        System.out.println(user);
        if (user != null && Objects.equals(user.getId(), uid)) {
            return cartItemMapper.getCartBill(uid);
        } else {
            throw new AuthenticationException("你不具备查看他人账单的权限。");
        }
    }

    @Override
    public CartItemDO saveCartItem(Long uid, Long itemId, Integer num) {
        User user = jwtUserAuthenticationService.getCurrentUser();
        // 首先判断操作者是不是本人
        if (user != null && Objects.equals(user.getId(), uid)) {
            // 然后判断该购物车项本来有没有
            CartItemDO cartItemDO = cartItemMapper.getCartItem(uid, itemId);
            if (cartItemDO != null) {
                // 有就直接更新
                cartItemMapper.updateCartItem(cartItemDO.getId(), cartItemDO.getQuantity() + num);
            } else {
                // 没有就创建一个新的
                cartItemDO = new CartItemDO(uid, itemId, num);
                cartItemMapper.saveCartItem(cartItemDO);
            }
            return cartItemDO;
        } else {
            throw new AuthenticationException("你不具备操作他人购物车的权限。");
        }
    }

    @Override
    public Integer removeCartItem(Long uid, Long itemId, Integer num) {
        User user = jwtUserAuthenticationService.getCurrentUser();
        // 首先判断操作者是不是本人
        if (user != null && Objects.equals(user.getId(), uid)) {
            CartItemDO cartItemDO = cartItemMapper.getCartItem(uid, itemId);
            if (cartItemDO != null) {
                // 根据购物车项的数量进行操作
                return cartItemDO.getQuantity() - num <= 0 ? cartItemMapper.removeCartItem(cartItemDO.getId())
                        : cartItemMapper.updateCartItem(cartItemDO.getId(), cartItemDO.getQuantity() - num);
            } else {
                // 没有就什么都不做
                return 0;
            }
        } else {
            throw new AuthenticationException("你不具备操作他人购物车的权限。");
        }
    }
}
