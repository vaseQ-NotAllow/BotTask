package Messenger;

public class TgChatId extends ChatId {
    private long Id;

    public TgChatId(long id) {
        this.Id = id;
    }

    public long getId() {
        return Id;
    }

    @Override
    public int hashCode() {
        return (int) (Id % 2147483647);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TgChatId tgId = (TgChatId) obj;
        return Id == tgId.getId();
    }

    @Override
    ChatType getType() {
        return ChatType.Telegram;
    }
}
