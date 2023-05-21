package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LessKilocaloriesStrategy extends Strategy {
    private List<Product> listToFilter;

    public LessKilocaloriesStrategy(Double price, Double kilocalories, Customer customer) {
        super(price, kilocalories, customer);
    }

    @Override
    public List<Product> useStrategy(SmallBakery bakery) throws SearchProductsNotFoundException,
            DoNotHaveEnoughMoneyToBuyException {
        boolean loopCheck = true;
        List<Product> output = new ArrayList<>();
        listToFilter = bakery.getProducts();
        double priceToCheck = getPrice();
        double kilocaloriesToCheck = getKilocalories();
        (listToFilter).sort(Comparator.comparing(Product::getKilocalories).thenComparing(Product::getPrice));

        while (loopCheck) {
            for (int i = 0; i < listToFilter.size(); i++) {
                double checkerPrice = priceToCheck - listToFilter.get(i).getPrice();
                double checkerCalories = kilocaloriesToCheck - listToFilter.get(i).getKilocalories();
                if (checkerPrice > -1 && checkerCalories > -1) {
                    output.add(listToFilter.get(i));
                    priceToCheck = priceToCheck - listToFilter.get(i).getPrice();
                    kilocaloriesToCheck = kilocaloriesToCheck - listToFilter.get(i).getKilocalories();
                    removeProductWithSomeNameAndType(listToFilter.get(i));
                    break;
                }
                if (i == listToFilter.size() - 1) {
                    loopCheck = false;
                }
            }
        }
        if (getCustomer().getBankAccount() < getPrice() - priceToCheck) {
            throw new DoNotHaveEnoughMoneyToBuyException();
        }
        if (output.size() == 0) {
            throw new SearchProductsNotFoundException();
        } else {
            return output;
        }

    }

    public void removeProductWithSomeNameAndType(Product product) {
        for (int i = 0; i < getListToFilter().size(); i++) {
            if (getListToFilter().get(i).getName().equals(product.getName())
                    && getListToFilter().get(i).getTypes().equals(product.getTypes())) {
                listToFilter.remove(getListToFilter().get(i));
                i = -1;
            }
        }
    }

    public List<Product> getListToFilter() {
        return listToFilter;
    }
}
