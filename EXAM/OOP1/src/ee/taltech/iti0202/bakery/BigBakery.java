package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.baker.Baker;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BigBakery extends SmallBakery {
    public BigBakery(String name, Double bankAccount) {
        super(name, bankAccount);
    }

    @Override
    public void addProduct(Product product) throws CanNotAddProductToTheBakeryException {
        if (product.isInTheBakery()) {
            throw new CanNotAddProductToTheBakeryException();
        }
        if (!checkTypes(product)) {
            throw new CanNotAddProductToTheBakeryException();
        }
        products.add(product);
        product.setInTheBakery(true);
        logger.info(product.getName() + " has been added to the bakery with name: " + getName());
    }

    @Override
    public void addBaker(Baker baker) throws BakerAlreadyInTheBakeryException, BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        if (bakers.contains(baker)) {
            throw new BakerAlreadyInTheBakeryException();
        }
        if (baker.isWorks()) {
            throw new BakerAlreadyContainsInAnotherBakeryException();
        }

        bakers.add(baker);
        baker.setWorks(true);
        logger.info(baker.getName() + " has been added to the bakery with name: " + getName());

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

    public List<Product> findProductByType(Product.Types bakeryTypes) throws SearchProductsNotFoundException {
        List<Product> output = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getTypes(), bakeryTypes)) {
                output.add(products.get(i));
            }
        }
        if (output.size() == 0) {
            throw new SearchProductsNotFoundException();
        }
        return output;
    }

}
