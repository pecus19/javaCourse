package ee.taltech.iti0202.bakery.exceptions;

public class SearchProductsNotFoundException extends Exception {
    public static final String MESSAGE = "Search products not found!";

    /**
     * constructor
     */
    public SearchProductsNotFoundException() {
        super(MESSAGE);
    }
}

