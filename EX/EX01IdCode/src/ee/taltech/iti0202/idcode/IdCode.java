
package ee.taltech.iti0202.idcode;

public class IdCode {

    private final String idCodeValue;
    public static final int ELEVEN = 11;
    public static final int ONE = 1;
    public static final int TWELVE = 12;
    public static final int TWENTY = 20;
    public static final int TWENTY_ONE = 21;
    public static final int TWO_HUNDRED_TWENTY = 220;
    public static final int SEVEN_HUNDRED_TEN = 710;
    public static final int TWO_HUNDRED_TWENTY_TWO = 221;
    public static final int SIX_HUNDRED = 600;
    public static final int SIX_HUNDRED_FIFTY = 650;
    public static final int TWO_HUNDRED_SEVENTY = 270;
    public static final int THREE_HUNDRED_SEVENTY = 370;
    public static final int FOUR_HUNDRED_SEVENTY = 470;
    public static final int FIVE_HUNDRED_SEVENTY = 570;
    public static final int FOUR_HUNDRED_NINETY = 490;
    public static final int FIVE_HUNDRED_TWENTY = 520;
    public static final int FOUR_HUNDRED_TWENTY = 420;
    public static final int FOUR_HUNDRED = 400;
    public static final int TWO_THOUSAND = 2000;
    public static final int THOUSAND_EIGHT_HUNDRED = 1900;
    public static final int THIRTY_ONE = 31;
    public static final int THIRTY = 30;
    public static final int TWENTY_NINE = 29;
    public static final int TWENTY_EIGHT = 28;

    enum Gender {
        MALE, FEMALE
    }

    /**
     * Method returns the id code.
     *
     * @return id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }

    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
    }

    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        return isControlNumberCorrect() && isDayNumberCorrect() && isMonthNumberCorrect() && isYearNumberCorrect()
                && isDayNumberCorrect() && controlNumber(idCodeValue);
    }

    public boolean controlNumber(String idCodeValue) {
        int counter = 1;
        int output = 0;
        for (int i = 0; i < idCodeValue.length() - 1; i++) {
            output += Integer.parseInt(idCodeValue.substring(i, i + 1)) * counter;
            if (counter == 9) {
                counter = 1;
            } else {
                counter++;
            }
        }
        return Math.round(output) % ELEVEN == Integer.parseInt(String.valueOf(idCodeValue.charAt(10)));
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        final String str_day = idCodeValue.substring(5, 7);
        final String str_month = idCodeValue.substring(3, 5);

        return String.format("This is a %s born on %s.%s.%s in %s", getGender(), str_day, str_month, getFullYear(),
                getBirthPlace());
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        int sex = Integer.parseInt(String.valueOf(idCodeValue.charAt(0)));
        return switch (sex) {
            case 1, 3, 5 -> Gender.MALE;
            case 2, 4, 6 -> Gender.FEMALE;
            default -> null;
        };

    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        int place = Integer.parseInt(idCodeValue.substring(7, 10));
        if (place >= 1 && place <= 10) {
            return "Kuressaare";
        }
        if (place >= ELEVEN && place <= TWENTY) {
            return "Tartu";
        }
        if (place >= TWENTY_ONE && place <= TWO_HUNDRED_TWENTY) {
            return "Tallinn";
        }
        if (place >= TWO_HUNDRED_TWENTY_TWO && place <= TWO_HUNDRED_SEVENTY) {
            return "Kohtla-Järve";
        }
        if (place >= TWO_HUNDRED_SEVENTY + ONE && place <= THREE_HUNDRED_SEVENTY) {
            return "Tartu";
        }
        if (place >= (THREE_HUNDRED_SEVENTY + ONE) && place <= FOUR_HUNDRED_TWENTY) {
            return "Narva";
        }
        if (place >= (FOUR_HUNDRED_TWENTY + ONE) && place <= FOUR_HUNDRED_SEVENTY) {
            return "Pärnu";
        }
        if (place >= (FOUR_HUNDRED_SEVENTY + ONE) && place <= FOUR_HUNDRED_NINETY) {
            return "Tallinn";
        }
        if (place >= FOUR_HUNDRED_NINETY + ONE && place <= FIVE_HUNDRED_TWENTY) {
            return "Paide";
        }
        if (place >= (FIVE_HUNDRED_TWENTY + ONE) && place <= FIVE_HUNDRED_SEVENTY) {
            return "Rakvere";
        }
        if (place >= (FIVE_HUNDRED_SEVENTY + ONE) && place <= SIX_HUNDRED) {
            return "Valga";
        }
        if (place >= (SIX_HUNDRED + ONE) && place <= SIX_HUNDRED_FIFTY) {
            return "Viljandi";
        }
        if (place >= (SIX_HUNDRED_FIFTY) + ONE && place <= SEVEN_HUNDRED_TEN) {
            return "Võru";
        }

        return "unknown";
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.2
     */
    public int getFullYear() {
        int year = Integer.parseInt(idCodeValue.substring(1, 3));
        int sex = Integer.parseInt(String.valueOf(idCodeValue.charAt(0)));
        return switch (sex) {
            case 1, 2, 3, 4 -> THOUSAND_EIGHT_HUNDRED + year;
            case 5, 6 -> TWO_THOUSAND + year;
            default -> 0;
        };

    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        return getGender() != null;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        return getFullYear() != 0;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        int month = Integer.parseInt(idCodeValue.substring(3, 5));
        return switch (month) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ELEVEN, TWELVE -> true;
            default -> false;
        };
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        int day = Integer.parseInt(idCodeValue.substring(5, 7));
        int month = Integer.parseInt(idCodeValue.substring(3, 5));
        if (month == 1 && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        if (month == 2 && day >= 1 && day <= TWENTY_EIGHT && !isLeapYear(getFullYear())) {
            return true;
        }
        if (month == 2 && day >= 1 && day <= TWENTY_NINE && isLeapYear(getFullYear())) {
            return true;
        }
        if (month == 3 && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        if (month == 4 && day >= 1 && day <= THIRTY) {
            return true;
        }
        if (month == 5 && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        if (month == 6 && day >= 1 && day <= THIRTY) {
            return true;
        }
        if (month == 7 && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        if (month == 8 && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        if (month == 9 && day >= 1 && day <= THIRTY) {
            return true;
        }
        if (month == 10 && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        if (month == ELEVEN && day >= 1 && day <= THIRTY) {
            return true;
        }
        if (month == TWELVE && day >= 1 && day <= THIRTY_ONE) {
            return true;
        }
        return false;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        return idCodeValue.length() == ELEVEN;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        if (fullYear % 4 != 0) {
            return false;
        } else if (fullYear % FOUR_HUNDRED == 0) {
            return true;
        } else return fullYear % 100 != 0;
    }

    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("50402290232");
//        System.out.println(validMaleIdCode.controlNumber("49808270244"));
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
        System.out.println(validMaleIdCode.getFullYear());
        System.out.println(validMaleIdCode.isGenderNumberCorrect());
        System.out.println(validMaleIdCode.isYearNumberCorrect());
        System.out.println(validMaleIdCode.isMonthNumberCorrect());
        System.out.println(validMaleIdCode.isDayNumberCorrect());
        System.out.println(validMaleIdCode.isControlNumberCorrect());
        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

}
