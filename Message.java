import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Messages {

    private final List<Message> messageList = new ArrayList<>();
    private int numMessagesSent = 0;


    public String generateMessageID() {
        long min = 4_000_000_000L;
        long max = 8_888_888_888L;
        long id  = min + (long)(new Random().nextDouble() * (max - min));
        return String.valueOf(id);
    }


    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    // Vérifie le numéro du destinataire
    public String checkRecipientCell(String cellNumber) {
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        if (cellNumber != null && cellNumber.matches(regex)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain " +
                "an international code. Please correct the number and try again.";
    }


    public String validateMessage(String message) {
        if (message == null || message.length() > 250) {
            int excess = (message == null) ? 250 : message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
        return "Message ready to send.";
    }


    public String createMessageHash(String messageID, int messageNumber, String message) {
        String firstTwo = messageID.substring(0, 2);
        String[] words  = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord  = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }


    public String sentMessage(String choice) {
        if ("1".equals(choice)) return "Message successfully sent.";
        if ("2".equals(choice)) return "Press 0 to delete the message.";
        if ("3".equals(choice)) return "Message successfully stored.";
        return "Invalid selection. Please choose 1, 2, or 3.";
    }


    public void addMessage(Message msg) {
        messageList.add(msg);
        if ("Sent".equals(msg.getStatus())) {
            numMessagesSent++;
        }
    }


    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : messageList) {
            if ("Sent".equals(m.getStatus())) {
                sb.append("Message ID   : ").append(m.getMessageID()).append("\n");
                sb.append("Message Hash : ").append(m.getMessageHash()).append("\n");
                sb.append("Recipient    : ").append(m.getRecipient()).append("\n");
                sb.append("Message      : ").append(m.getMessageText()).append("\n");
                sb.append("-".repeat(40)).append("\n");
            }
        }
        return sb.length() == 0 ? "No messages sent." : sb.toString();
    }


    public int returnTotalMessages() {
        return numMessagesSent;
    }


    public void storeMessage(Message msg) {
        String filename = "stored_messages.json";

        String jsonEntry = "{\n" +
                "  \"messageID\": \""      + msg.getMessageID()      + "\",\n" +
                "  \"messageNumber\": "    + msg.getMessageNumber()   + ",\n"  +
                "  \"recipient\": \""      + msg.getRecipient()       + "\",\n" +
                "  \"messageText\": \""    + msg.getMessageText()     + "\",\n" +
                "  \"messageHash\": \""    + msg.getMessageHash()     + "\",\n" +
                "  \"status\": \""         + msg.getStatus()          + "\"\n"  +
                "}";

        try {

            FileWriter writer = new FileWriter(filename, true);
            writer.write(jsonEntry + "\n");
            writer.close();
            System.out.println("Message stored in " + filename);
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
}