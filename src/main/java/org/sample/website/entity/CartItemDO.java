package org.sample.website.entity;

public class CartItemDO {

    private Long id;
    private Long user_id;
    private Long item_id;
    private Integer quantity;

    public CartItemDO() {
    }

    public CartItemDO(Long user_id, Long item_id, Integer quantity) {
        this.user_id = user_id;
        this.item_id = item_id;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "CartItemDO{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", item_id=" + item_id +
                ", quantity=" + quantity +
                '}';
    }
}
