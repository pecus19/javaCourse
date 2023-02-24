package ee.taltech.iti0202.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    /**
     * @param kittens kittens
     */
    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    /**
     * @return OptionalDouble
     */
    public OptionalDouble findKittensAverageAge() {
        return kittens.stream().mapToInt(Kitten::getAge).average();
    }

    /**
     * @return Optional<Kitten>
     */
    public Optional<Kitten> findOldestKitten() {
        return kittens.stream().max(Comparator.comparing(Kitten::getAge));
    }

    /**
     * @return List<Kitten>
     */
    public List<Kitten> findYoungestKittens() {
//        return kittens.stream().min(Comparator.comparing(Kitten::getAge)).stream().collect(Collectors.toList());
        int minAge = kittens.stream()
                .mapToInt(Kitten::getAge)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));

        return kittens.stream()
                .filter(kitten -> kitten.getAge() == minAge)
                .collect(Collectors.toList());
    }

    /**
     * @param gender gender
     * @return List<Kitten>
     */
    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        return kittens.stream().filter(kitten -> kitten.getGender() == gender).collect(Collectors.toList());
    }

    /**
     * @param minAge minAge
     * @param maxAge maxAge
     * @return List<Kitten>
     */
    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        return kittens.stream()
                .filter(kitten -> kitten.getAge() >= minAge && kitten.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    /**
     * @param givenName givenName
     * @return Optional<Kitten>
     */
    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        return kittens.stream().filter(kitten -> kitten.getName().equals(givenName)).findFirst();
    }

    /**
     * @return List<Kitten>
     */
    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream().sorted(Comparator.comparing(Kitten::getAge)).collect(Collectors.toList());
    }

    /**
     * @return List<Kitten>
     */
    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream().sorted(Comparator.comparing(Kitten::getAge).reversed()).collect(Collectors.toList());
    }
}
