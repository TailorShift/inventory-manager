package io.hackfest.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovementEntity extends PanacheEntityBase {
    @Id
    public Long id;

    public Long shopId;

    public Long productId;

    public Long receiptId;

    public Long returnId;

    @Enumerated(EnumType.STRING)
    public Size size;

    public String color;

    public Integer quantity;

    public static List<AvailableItem> getAvailableItemsInAllShops(long productId) {
        return find("""
                SELECT e.shopId, e.size, e.color, SUM(e.quantity) as quantity
                FROM InventoryMovementEntity e
                WHERE e.productId = ?1
                GROUP BY e.shopId, e.size, e.color
                HAVING SUM(e.quantity) > 0
                """, productId)
                .project(AvailableItem.class)
                .stream().toList();
    }
}
