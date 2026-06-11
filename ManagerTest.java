import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManagerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        
        Manager.addMessage(
                "John",
                "MSG001",
                "Alice",
                "Hello Alice",
                "HASH001");

        Manager.addMessage(
                "Peter",
                "MSG002",
                "Bob",
                "This is a longer message for testing",
                "HASH002");
    }

    @Test
    void testSearchByMessageIdFound() {
        Manager.searchByMessageId("MSG001");

        String output = outputStream.toString();

        assertTrue(output.contains("Recipient: Alice"));
        assertTrue(output.contains("MessageApp: Hello Alice"));
    }

    @Test
    void testSearchByMessageIdNotFound() {
        Manager.searchByMessageId("INVALID");

        String output = outputStream.toString();

        assertTrue(output.contains("MessageApp ID not found."));
    }

    @Test
    void testSearchMessagesByRecipientFound() {
        Manager.searchMessagesByRecipient("Bob");

        String output = outputStream.toString();

        assertTrue(output.contains("This is a longer message for testing"));
    }

    @Test
    void testSearchMessagesByRecipientNotFound() {
        Manager.searchMessagesByRecipient("Unknown");

        String output = outputStream.toString();

        assertTrue(output.contains("No messages found for recipient: Unknown"));
    }

    @Test
    void testDisplayLongestMessage() {
        Manager.displayLongestMessage();

        String output = outputStream.toString();

        assertTrue(output.contains("This is a longer message for testing"));
    }

    @Test
    void testDeleteMessageByHash() {
        Manager.deleteMessageByHash("HASH001");

        String output = outputStream.toString();

        assertTrue(output.contains("Deleting message: Hello Alice"));
    }

    @Test
    void testDeleteMessageByHashNotFound() {
        Manager.deleteMessageByHash("INVALID_HASH");

        String output = outputStream.toString();

        assertTrue(output.contains("Hash not found."));
    }

    @Test
    void testDisplaySendersAndRecipients() {
        Manager.displaySendersAndRecipients();

        String output = outputStream.toString();

        assertTrue(output.contains("Sender: John, Recipient: Alice"));
        assertTrue(output.contains("Sender: Peter, Recipient: Bob"));
    }

    @Test
    void testDisplayFullReport() {
        Manager.displayFullReport();

        String output = outputStream.toString();

        assertTrue(output.contains("Sender: John"));
        assertTrue(output.contains("Recipient: Alice"));
        assertTrue(output.contains("MessageApp ID: MSG001"));
        assertTrue(output.contains("Hash: HASH001"));
    }
}
