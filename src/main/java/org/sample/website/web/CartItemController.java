package org.sample.website.web;

import org.sample.website.annotation.Authentication;
import org.sample.website.entity.CartItemDO;
import org.sample.website.model.CartItemModel;
import org.sample.website.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/cart_items")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Authentication
    @GetMapping
    public ResponseEntity<?> listCartItem(Long uid) {
        List<CartItemModel> cartItemModels = cartItemService.listCartItem(uid);
        return new ResponseEntity<>(cartItemModels, HttpStatus.OK);
    }

    @Authentication
    @PostMapping
    public ResponseEntity<?> addCartItem(Long uid, Long itemId, Integer num) {
        CartItemDO cartItemDO = cartItemService.saveCartItem(uid, itemId, num);
        return new ResponseEntity<>(cartItemDO, HttpStatus.CREATED);
    }

    @Authentication
    @DeleteMapping
    public ResponseEntity<?> removeCartItem(Long uid, Long itemId, Integer num) {
        Integer integer = cartItemService.removeCartItem(uid, itemId, num);
        return new ResponseEntity<>(integer, HttpStatus.OK);
    }
}
