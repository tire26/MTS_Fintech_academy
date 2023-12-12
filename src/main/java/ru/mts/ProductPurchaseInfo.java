package ru.mts;

import java.util.Objects;
import java.util.Optional;

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

    public ProductPurchaseInfo(Integer productCount, Double productCoast, Double discount) {
        if (productCount <= 0) {
            throw new IllegalArgumentException("Количество продуктов должно быть больше 0");
        }
        if (productCoast <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше 0");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть между 0 и 100 процентов");
        }

        this.productCount = productCount;
        this.productCoast = productCoast;
        this.discount = discount;
    }
    public static Optional<Double> getPurchaseSumWithoutDiscount(ProductPurchaseInfo productPurchaseInfo) {
        Optional<Double> result = Optional.empty();
        if (!Objects.isNull(productPurchaseInfo)) {
            Double doubleResult = productPurchaseInfo.getProductCount() * productPurchaseInfo.getProductCoast();
            result = Optional.of(doubleResult);
        }
        return result;
    }

    public static Optional<Double> getPurchaseSumWithDiscount(ProductPurchaseInfo productPurchaseInfo) {
        Optional<Double> result = Optional.empty();
        if (!Objects.isNull(productPurchaseInfo)) {
            Double sumWithoutDiscount = getPurchaseSumWithoutDiscount(productPurchaseInfo).get();
            Double doubleResult = sumWithoutDiscount - sumWithoutDiscount * productPurchaseInfo.getDiscount() / 100;
            result = Optional.of(doubleResult);
        }
        return result;
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
