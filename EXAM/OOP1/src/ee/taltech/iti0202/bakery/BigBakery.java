package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.exceptions.ProductAlreadyContainsInAnotherBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductDoesNotContainsInBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductLimitExceededException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BigBakery extends SmallBakery {
    public BigBakery(String name, Double bankAccount) {
        super(name, bankAccount);
    }

    @Override
    public void addProduct(Product product) throws ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInAnotherBakeryException, ProductLimitExceededException {
        if (product.isInTheBakery()) {
            throw new ProductAlreadyContainsInAnotherBakeryException();
        }
        if (!products.contains(product)) {
            products.add(product);
            product.setInTheBakery(true);
            logger.info(product.getName() + " has been added to the bakery with name: " + getName());
        } else {
            throw new ProductDoesNotContainsInBakeryException();
        }
    }

    @Override
    public void removeProduct(Product product) throws ProductDoesNotContainsInBakeryException {
        if (products.contains(product)) {
            products.remove(product);
            product.setInTheBakery(false);
            logger.info(product.getName() + " has been removed from the bakery with name: " + getName());
        } else {
            throw new ProductDoesNotContainsInBakeryException();
        }
    }

    public List<Product> findProductByType(Product.bakeryTypes bakeryTypes) throws SearchProductsNotFoundException {
        List<Product> output = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getBakeryTypes(), bakeryTypes)) {
                output.add(products.get(i));
            }
        }
        if (output.size() == 0) {
            throw new SearchProductsNotFoundException();
        }
        return output;
    }

}
