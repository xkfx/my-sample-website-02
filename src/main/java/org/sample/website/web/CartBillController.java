package org.sample.website.web;

import org.sample.website.annotation.Authentication;
import org.sample.website.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/cart_bill")
public class CartBillController {

    private final CartItemService cartItemService;

    @Autowired
    public CartBillController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Authentication
    @GetMapping
    public ResponseEntity<?> getCartBill(Long uid) {
        return new ResponseEntity<>(cartItemService.getBill(uid), HttpStatus.OK);
    }
}
