package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DifferentTypesOfProductsStrategy extends Strategy {
    private List<Product> listToFilter;

    public DifferentTypesOfProductsStrategy(Double price, Double kilocalories, Customer customer) {
        super(price, kilocalories, customer);
    }

    @Override
    public List<Product> useStrategy(SmallBakery bakery) throws SearchProductsNotFoundException, DoNotHaveEnoughMoneyToBuyException {
        boolean loopCheck = true;
        List<Product> output = new ArrayList<>();
        listToFilter = bakery.getProducts();
        double priceToCheck = getPrice();
        (listToFilter).sort(Comparator.comparing(Product::getPrice));

        while (loopCheck) {
            if (listToFilter.size() == 0) {
                loopCheck = false;
            }
            for (int i = 0; i < listToFilter.size(); i++) {
                double checkerPrice = priceToCheck - listToFilter.get(i).getPrice();
                if (checkerPrice > -1) {
                    output.add(listToFilter.get(i));
                    priceToCheck = priceToCheck - listToFilter.get(i).getPrice();
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
            if (getListToFilter().get(i).getBakerytypes().equals(product.getBakerytypes())) {
                listToFilter.remove(getListToFilter().get(i));
                i = -1;
            }
        }
    }

    public List<Product> getListToFilter() {
        return listToFilter;
    }
}
