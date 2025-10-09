package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicatePerson;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommandPerson}.
 */
public class FindCommandPersonTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicatePerson firstPredicate =
                new NameContainsKeywordsPredicatePerson(Collections.singletonList("first"));
        NameContainsKeywordsPredicatePerson secondPredicate =
                new NameContainsKeywordsPredicatePerson(Collections.singletonList("second"));

        FindCommandPerson findFirstCommand = new FindCommandPerson(firstPredicate);
        FindCommandPerson findSecondCommand = new FindCommandPerson(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommandPerson findFirstCommandCopy = new FindCommandPerson(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicatePerson predicate = preparePredicate(" ");
        FindCommandPerson command = new FindCommandPerson(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicatePerson predicate = preparePredicate("Kurz Elle Kunz");
        FindCommandPerson command = new FindCommandPerson(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicatePerson predicate =
                new NameContainsKeywordsPredicatePerson(Arrays.asList("keyword"));
        FindCommandPerson findCommand = new FindCommandPerson(predicate);
        String expected = FindCommandPerson.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicatePerson}.
     */
    private NameContainsKeywordsPredicatePerson preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicatePerson(Arrays.asList(userInput.split("\\s+")));
    }
}
