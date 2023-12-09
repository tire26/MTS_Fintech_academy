package mts.ru;

import java.text.DecimalFormat;

public class Main {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void main(String[] args) {
        ProductPurchaseInfo productPurchaseInfo1 = new ProductPurchaseInfo(
                100,
                121.75,
                0.75
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


    }

    public static void printPurchaseSums(ProductPurchaseInfo productPurchaseInfo) {
        System.out.println("Сумма без скидки: " + decimalFormat.format(ProductPurchaseInfo.getPurchaseSumWithoutDiscount(productPurchaseInfo)));
        System.out.println("Сумма со скидкой: " + decimalFormat.format(ProductPurchaseInfo.getPurchaseSumWithDiscount(productPurchaseInfo)));
    }
}