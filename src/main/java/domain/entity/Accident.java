package domain.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Accident implements Entity {
    private int id;
    private String description;
    private BigDecimal materialDamage;
    private Date date;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMaterialDamage() {
        return materialDamage;
    }

    public void setMaterialDamage(BigDecimal materialDamage) {
        this.materialDamage = materialDamage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}