
package ee.taltech.iti0202.idcode;

public class IdCode {

    private final String idCodeValue;

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
                && isDayNumberCorrect();
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        final String str_day = idCodeValue.substring(5, 7);
        final String str_month = idCodeValue.substring(3, 5);

        return String.format("This is a %s born on %s.%s.%s in %s.", getGender(), str_day, str_month, getFullYear(),
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
        if (place >= 11 && place <= 20) {
            return "Tartu";
        }
        if (place >= 21 && place <= 220) {
            return "Tallinn";
        }
        if (place >= 221 && place <= 270) {
            return "Kohtla-Järve";
        }
        if (place >= 271 && place <= 370) {
            return "Tartu";
        }
        if (place >= 371 && place <= 420) {
            return "Narva";
        }
        if (place >= 421 && place <= 470) {
            return "Pärnu";
        }
        if (place >= 471 && place <= 490) {
            return "Tallinn";
        }
        if (place >= 491 && place <= 520) {
            return "Paide";
        }
        if (place >= 521 && place <= 570) {
            return "Rakvere";
        }
        if (place >= 571 && place <= 600) {
            return "Valga";
        }
        if (place >= 601 && place <= 660) {
            return "Viljandi";
        }
        if (place >= 651 && place <= 710) {
            return "Võru";
        }

        return "Unknown";
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
            case 1, 2, 3, 4 -> 1900 + year;
            case 5, 6 -> 2000 + year;
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
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> true;
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
        if (month == 1 && day >= 1 && day <= 31) {
            return true;
        }
        if (month == 2 && day >= 1 && day <= 28 && !isLeapYear(getFullYear())) {
            return true;
        }
        if (month == 2 && day >= 1 && day <= 29 && isLeapYear(getFullYear())) {
            return true;
        }
        if (month == 3 && day >= 1 && day <= 31) {
            return true;
        }
        if (month == 4 && day >= 1 && day <= 30) {
            return true;
        }
        if (month == 5 && day >= 1 && day <= 31) {
            return true;
        }
        if (month == 6 && day >= 1 && day <= 30) {
            return true;
        }
        if (month == 7 && day >= 1 && day <= 31) {
            return true;
        }
        if (month == 8 && day >= 1 && day <= 31) {
            return true;
        }
        if (month == 9 && day >= 1 && day <= 30) {
            return true;
        }
        if (month == 10 && day >= 1 && day <= 31) {
            return true;
        }
        if (month == 11 && day >= 1 && day <= 30) {
            return true;
        }
        if (month == 12 && day >= 1 && day <= 31) {
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
        return idCodeValue.length() == 11;
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
        } else if (fullYear % 400 == 0) {
            return true;
        } else if (fullYear % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
//        IdCodeNew validMaleIdCode = new IdCodeNew("37605030299");
        IdCode validMaleIdCode = new IdCode("50202260892");
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
