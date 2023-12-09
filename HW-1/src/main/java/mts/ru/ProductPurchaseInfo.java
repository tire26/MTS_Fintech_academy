package mts.ru;


/**
 * ProductPurchaseInfo - класс хранящий информацию
 * о покупке одного типа товара
 */
public class ProductPurchaseInfo {
    // колличество товаров
    private Integer productCount;
    // стоимость 1 еденицы товара
    private Double productCoast;
    // процент скидки на покупку
    private Double discount;

    public ProductPurchaseInfo(Integer productCount,
                               Double productCoast,
                               Double discount) {
        this.productCount = productCount;
        this.productCoast = productCoast;
        this.discount = discount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public Double getProductCoast() {
        return productCoast;
    }

    public Double getDiscount() {
        return discount;
    }

}
