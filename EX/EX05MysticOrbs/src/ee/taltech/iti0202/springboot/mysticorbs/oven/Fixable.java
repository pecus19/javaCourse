package ee.taltech.iti0202.springboot.mysticorbs.oven;

import ee.taltech.iti0202.springboot.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    /**
     * @throws CannotFixException CannotFixException
     */
    void fix() throws CannotFixException;

    /**
     * @return int
     */
    int getTimesFixed();
}
