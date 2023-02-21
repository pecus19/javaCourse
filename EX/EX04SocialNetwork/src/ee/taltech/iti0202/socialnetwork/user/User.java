package ee.taltech.iti0202.socialnetwork.user;

public class User {
    private String name;
    private Integer age;

    /**
     * @param name name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * @param name name
     * @param age  age
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
