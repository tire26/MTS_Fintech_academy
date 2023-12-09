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
        printPurchaseSum(productPurchaseInfo1);

        ProductPurchaseInfo productPurchaseInfo2 = new ProductPurchaseInfo(
                1215,
                1212.25,
                42.575
        );
        printPurchaseSum(productPurchaseInfo2);

        ProductPurchaseInfo productPurchaseInfo3 = new ProductPurchaseInfo(
                2,
                150000.00,
                59.1
        );
        printPurchaseSum(productPurchaseInfo3);


    }

    public static void printPurchaseSum(ProductPurchaseInfo productPurchaseInfo) {
        Double sumWithoutDiscount = productPurchaseInfo.getProductCount() * productPurchaseInfo.getProductCoast();
        System.out.println(decimalFormat.format(sumWithoutDiscount));

        Double sumWithDiscount = sumWithoutDiscount - sumWithoutDiscount * productPurchaseInfo.getDiscount() / 100;
        System.out.println(decimalFormat.format(sumWithDiscount));
    }
}