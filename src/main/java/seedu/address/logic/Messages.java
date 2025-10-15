package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.animal.Animal;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_NAME = "The person's name provided is invalid";
    public static final String MESSAGE_INVALID_ANIMAL_DISPLAYED_NAME = "The animal's name provided is invalid";

    public static final String MESSAGE_FIND_PERSON_SUCCESS = "Found %1$s people!";
    public static final String MESSAGE_FIND_ANIMAL_SUCCESS = "Found %1$s animals!";

    public static final String MESSAGE_DELETED_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETED_ANIMAL_SUCCESS = "Deleted Animal: %1$s";

    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "";
    public static final String MESSAGE_INVALID_TYPE = " The contact type provided is invalid, "
            + " valid type: person, animal";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code animal} for display to the user.
     */
    public static String format(Animal animal) {
        final StringBuilder builder = new StringBuilder();
        builder.append(animal.getName())
                .append("; Description: ")
                .append(animal.getDescription())
                .append("; Location: ")
                .append(animal.getLocation());
        return builder.toString();
    }

}
