package ru.mts;

import java.text.DecimalFormat;
import java.util.Optional;

public class Main {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void main(String[] args) {
        ProductPurchaseInfo productPurchaseInfo1 = new ProductPurchaseInfo(
                100,
                121.75,
                99.0
        );
        printPurchaseSums(productPurchaseInfo1);

        ProductPurchaseInfo productPurchaseInfo2 = new ProductPurchaseInfo(
                1215,
                1212.25,
                42.575
        );
        printPurchaseSums(productPurchaseInfo2);

        ProductPurchaseInfo productPurchaseInfo3 = new ProductPurchaseInfo(
                2,
                150000.00,
                59.1
        );
        printPurchaseSums(productPurchaseInfo3);

        printPurchaseSums(null);
    }

    public static void printPurchaseSums(ProductPurchaseInfo productPurchaseInfo) {
        Optional<Double> resultWithoutDiscount = ProductPurchaseInfo.getPurchaseSumWithoutDiscount(productPurchaseInfo);
        Optional<Double> resultWithDiscount = ProductPurchaseInfo.getPurchaseSumWithDiscount(productPurchaseInfo);

        resultWithoutDiscount.ifPresentOrElse(
                aDouble -> System.out.println("Сумма без скидки: " + decimalFormat.format(aDouble)),
                () -> System.out.println("Указанный объект имеет null значение"));
        resultWithDiscount.ifPresent(aDouble -> System.out.println("Сумма со скидкой: " + decimalFormat.format(aDouble)));
    }
}