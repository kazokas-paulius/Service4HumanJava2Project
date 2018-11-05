package s4h.entity;
//VYGANTAS
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "languages")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
//	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lang_id")
	private int langId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "first", nullable = false)
	@NotBlank // " ", " ", "\n", null
	private String first;

	@Column(name = "second", nullable = false)
	@NotBlank // " ", " ", "\n", null
	private String second;

	@Column
	private byte work;
	@Column
	private LocalDate free;
	@Column
	private byte rating;

	@Column
	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String langFirst) {
		this.first = langFirst;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String langSecond) {
		this.second = langSecond;
	}

	public byte getWork() {
		return work;
	}

	public void setWork(byte work) {
		this.work = work;
	}

	public LocalDate getFree() {
		return free;
	}

	public void setFree(LocalDate free) {
		this.free = free;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Language [langId=" + langId + ", userId=" + userId + ", first=" + first + ", second=" + second
				+ ", work=" + work + ", free=" + free + ", rating=" + rating + ", price=" + price + "]";
	}
}
