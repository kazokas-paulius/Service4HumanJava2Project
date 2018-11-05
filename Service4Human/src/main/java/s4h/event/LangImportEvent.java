package s4h.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LangImportEvent implements Serializable {

    private int total;
    private int free;
    private LocalDateTime timestamp;

    public LangImportEvent(LocalDateTime timestamp, int total, int free) {
	this.timestamp = timestamp;
	this.total = total;
	this.free = free;
    }

     public LocalDateTime getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public int getFree() {
	return free;
    }

    public void setFree(int free) {
	this.free = free;
    }

}
