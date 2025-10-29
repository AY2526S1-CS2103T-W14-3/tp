package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.animal.Animal;

public class AnimalBuilderTest {

    @Test
    public void build_defaultAnimal_success() {
        AnimalBuilder builder = new AnimalBuilder();
        Animal animal = builder.build();
        assertNotNull(animal);
    }

    @Test
    public void build_withCustomValues_success() {
        AnimalBuilder builder = new AnimalBuilder();
        Animal animal = builder
                .withName("Test Animal")
                .withDescription("Test Description")
                .withLocation("Test Location")
                .build();
        assertNotNull(animal);
    }
}
