package ee.taltech.iti0202.springboot.mysticorbs.exceptions;

import ee.taltech.iti0202.springboot.mysticorbs.oven.Oven;

public class CannotFixException extends Throwable {
    private Oven oven;
    private Reason reason;

    public enum Reason {
        IS_NOT_BROKEN,
        FIXED_MAXIMUM_TIMES,
        NOT_ENOUGH_RESOURCES
    }

    /**
     * @param oven   smt.
     * @param reason smt.
     */
    public CannotFixException(Oven oven, Reason reason) {
        this.oven = oven;
        this.reason = reason;
    }

    public Oven getOven() {
        return oven;
    }

    public Reason getReason() {
        return reason;
    }
}
