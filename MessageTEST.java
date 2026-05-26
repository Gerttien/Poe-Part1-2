import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {

    Messages messages = new Messages();

    @Test
    void testMessageLengthSuccess() {
        String msg = "Hi Mike, can you join us for dinner tonight?";
        assertEquals("Message ready to send.", messages.validateMessage(msg));
    }

    @Test
    void testMessageLengthFailure() {
        String longMsg = "A".repeat(260);
        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                messages.validateMessage(longMsg)
        );
    }


    @Test
    void testRecipientCellSuccess() {
        assertEquals(
                "Cell phone number successfully captured.",
                messages.checkRecipientCell("+27718693002")
        );
    }

    @Test
    void testRecipientCellFailure() {
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain " +
                        "an international code. Please correct the number and try again.",
                messages.checkRecipientCell("08575975889")
        );
    }


    @Test
    void testMessageHashTestCase1() {
        String hash = messages.createMessageHash(
                "0012345678", 0,
                "Hi Mike, can you join us for dinner tonight?"
        );
        assertEquals("00:0:HITONIGHT", hash);
    }

    @Test
    void testMessageHashesInLoop() {
        String[][] testData = {
                {"0012345678", "0", "Hi Mike, can you join us for dinner tonight?", "00:0:HITONIGHT"},
                {"1234567890", "1", "Hi Keegan, did you receive the payment?",      "12:1:HIPAYMENT"}
        };
        for (String[] data : testData) {
            String result = messages.createMessageHash(
                    data[0], Integer.parseInt(data[1]), data[2]
            );
            assertEquals(data[3], result);
        }
    }


    @Test
    void testMessageIDCreated() {
        String id = messages.generateMessageID();
        System.out.println("Message ID generated: " + id);
        assertTrue(messages.checkMessageID(id));
        assertEquals(10, id.length());
    }


    @Test
    void testSentMessageSend() {
        assertEquals("Message successfully sent.", messages.sentMessage("1"));
    }

    @Test
    void testSentMessageDisregard() {
        assertEquals("Press 0 to delete the message.", messages.sentMessage("2"));
    }

    @Test
    void testSentMessageStore() {
        assertEquals("Message successfully stored.", messages.sentMessage("3"));
    }
}