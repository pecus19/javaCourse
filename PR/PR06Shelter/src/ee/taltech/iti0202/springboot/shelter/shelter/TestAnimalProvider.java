package ee.taltech.iti0202.springboot.shelter.shelter;

import ee.taltech.iti0202.springboot.shelter.animal.Animal;
import ee.taltech.iti0202.springboot.shelter.animalprovider.AnimalProvider;

import java.util.List;

public class TestAnimalProvider implements AnimalProvider {
    @Override
    public List<Animal> provide(Animal.Type type) {
        // here some code
        return null;
    }
}
