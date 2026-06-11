public class Manager {


    private static int size = 100;
    private static String[] messageIds = new String[size];
    private static String[] recipients = new String[size];
    private static String[] messages = new String[size];
    private static String[] hashes = new String[size];
    private static String[] senders = new String[size];

    private static int messageCount = 0;

    public static void addMessage (String sender, String id, String recipient, String message, String hash){
        if (messageCount < size) {
            senders[messageCount] = sender;
            messageIds[messageCount] = id;
            recipients[messageCount] = recipient;
            messages[messageCount] = message;
            hashes[messageCount] = hash;
            messageCount++;
        } else {
            System.out.println("MessageApp storage is full.");
        }
    }
    public static void displaySendersAndRecipients () {
        System.out.println("Senders and Recipients:");
        for (int i = 0; i < messageCount; i++) {
            System.out.println("Sender: " + senders[i] + ", Recipient: " + recipients[i]);
        }
    }
    public static void displayLongestMessage () {
        if (messageCount == 0) {
            System.out.println("No messages to display.");
            return;
        }

        int maxIndex = 0;
        for (int i = 1; i < messageCount; i++) {
            if (messages[i].length() > messages[maxIndex].length()) {
                maxIndex = i;
            }
        }
        System.out.println("Longest MessageApp: " + messages[maxIndex]);
    }

    public static void searchByMessageId (String id){
        for (int i = 0; i < messageCount; i++) {
            if (messageIds[i].equals(id)) {
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("MessageApp: " + messages[i]);
                return;
            }
        }
        System.out.println("MessageApp ID not found.");
    }
    public static void searchMessagesByRecipient (String recipient){
        boolean found = false;
        for (int i = 0; i < messageCount; i++) {
            if (recipients[i].equals(recipient)) {
                System.out.println("MessageApp to " + recipient + ": " + messages[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for recipient: " + recipient);
        }
    }
    public static void deleteMessageByHash (String hash){
        for (int i = 0; i < messageCount; i++) {
            if (hashes[i].equals(hash)) {
                System.out.println("Deleting message: " + messages[i]);
                // Shift elements to fill the gap
                for (int j = i; j < messageCount - 1; j++) {
                    senders[j] = senders[j + 1];
                    messageIds[j] = messageIds[j + 1];
                    recipients[j] = recipients[j + 1];
                    messages[j] = messages[j + 1];
                    hashes[j] = hashes[j + 1];
                }
                messageCount--;
                return;
            }
        }
        System.out.println("Hash not found.");
    }
    public static void displayFullReport () {
        System.out.println("Full MessageApp Report:");
        for (int i = 0; i < messageCount; i++) {
            System.out.println("Sender: " + senders[i]);
            System.out.println("Recipient: " + recipients[i]);
            System.out.println("MessageApp ID: " + messageIds[i]);
            System.out.println("MessageApp: " + messages[i]);
            System.out.println("Hash: " + hashes[i]);
            System.out.println("-----");
        }
    }
}

