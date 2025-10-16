package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.AnimalName;
import seedu.address.model.animal.Description;
import seedu.address.model.animal.Location;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new PersonName("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com")),
            new Person(new PersonName("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com")),
            new Person(new PersonName("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com")),
            new Person(new PersonName("David Li"), new Phone("91031282"), new Email("lidavid@example.com")),
            new Person(new PersonName("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com")),
            new Person(new PersonName("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"))
        };
    }

    public static Animal[] getSampleAnimals() {
        return new Animal[] {
            new Animal(new AnimalName("Max"),
                new Description("Golden Retriever, friendly and well-trained"),
                new Location("Block A, Kennel 1")),
            new Animal(new AnimalName("Luna"),
                new Description("Persian cat, white fur, blue eyes"),
                new Location("Block B, Cat Room 3")),
            new Animal(new AnimalName("Charlie"),
                new Description("German Shepherd, good guard dog"),
                new Location("Block A, Kennel 5")),
            new Animal(new AnimalName("Bella"),
                new Description("Siamese cat, very playful"),
                new Location("Block B, Cat Room 2"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Animal sampleAnimal : getSampleAnimals()) {
            sampleAb.addAnimal(sampleAnimal);
        }
        return sampleAb;
    }

}
