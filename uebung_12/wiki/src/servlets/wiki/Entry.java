package servlets.wiki;

public class Entry
{
    private String text = "";
    private boolean isLocked;
    private String lockedBy = "";

    public String getText()
    {
        synchronized (text)
        {
            return text;
        }
    }

    public synchronized void setText(String text)
    {
        synchronized (this.text)
        {
            this.text = text;
        }
    }

    public void lock(String sessionId)
    {
        synchronized (this.lockedBy)
        {
            isLocked = true;
            lockedBy = sessionId;
        }
    }

    public void unlock(String sessionId)
    {
        synchronized (this.lockedBy)
        {
            if (sessionId.equalsIgnoreCase(lockedBy))
            {
                isLocked = false;
                lockedBy = "";
            }
        }
    }

    public String getLockedBy()
    {
        synchronized (this.lockedBy)
        {
            return lockedBy;
        }
    }
}