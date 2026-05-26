class Message {


    public String messageID;
    public int    messageNumber;
    public String recipient;
    public String messageText;
    public String messageHash;
    public String status;


    public Message(String messageID, int messageNumber,
                   String recipient, String messageText, String messageHash) {
        this.messageID     = messageID;
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.messageText   = messageText;
        this.messageHash   = messageHash;
        this.status        = "Pending";
    }

    public String getMessageID()       { return messageID;     }
    public int    getMessageNumber()   { return messageNumber; }
    public String getRecipient()       { return recipient;     }
    public String getMessageText()     { return messageText;   }
    public String getMessageHash()     { return messageHash;   }
    public String getStatus()          { return status;        }


    public void setStatus(String status) { this.status = status; }
}
