package org.sample.website.model;

public class CartItemModel {

    private Long id;
    private Long user_id;
    private Long item_id;
    private Integer quantity;

    // 冗余
    private String item_name;

    public CartItemModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public String toString() {
        return "CartItemModel{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", item_id=" + item_id +
                ", quantity=" + quantity +
                ", item_name=" + item_name +
                '}';
    }
}
