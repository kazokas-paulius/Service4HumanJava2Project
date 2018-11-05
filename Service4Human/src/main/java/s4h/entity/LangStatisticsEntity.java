package s4h.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import s4h.event.LangImportEvent;

@Entity
@Table(name = "statistics")
public class LangStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "total")
    private int total;
    @Column(name = "free")
    private int free;
    private LocalDateTime timestamp;

    public static LangStatisticsEntity from(LangImportEvent event) {
	LangStatisticsEntity entity = new LangStatisticsEntity();
	entity.setTotal(event.getTotal());
	entity.setFree(event.getFree());
	entity.setTimestamp(event.getTimestamp());
	return entity;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

    public LocalDateTime getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
    }

}
