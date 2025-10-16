package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonName;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments and {@code String} of type in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // arguments after "edit"
        if (args.isEmpty()) {
            throw new ParseException(EditCommand.MESSAGE_USAGE);
        }

        String[] parts = args.trim().split(" ", 2);
        String type = parts[0].toLowerCase().trim();
        String remainArgs = parts.length > 1 ? parts[1] : "";
        System.out.println(remainArgs);

        if (remainArgs.isEmpty()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        switch (type) {

        case "person":
            return editPersonParse(remainArgs);

        default:
            throw new ParseException(MESSAGE_INVALID_TYPE);
        }
    }

    /**
     * Parses the given {@code String args} in the context of an EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     *
     * @param args the input arguments
     * @return the constructed EditPersonCommand
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditPersonCommand editPersonParse(String args) throws ParseException {
        System.out.println(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        String personName = argMultimap.getPreamble().trim();

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parsePersonName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(new PersonName(personName), editPersonDescriptor);
    }
}
