package s4h.entity;
//VYGANTAS
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username", nullable = false)
	@NotBlank // " ", " ", "\n", null
	@Size(min = 3, max = 25)
	private String username;

	@Column(name = "password", nullable = false)
	@Size(min = 3, max = 20)
	private String password;

	@Column(name = "name")
	private String name;

	@Size(max = 40)
	private String mail;

	@Column
	private String sort;

	public User() {
	}

	public User(String username, String password, String name, String mail, String sort) {
//		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.mail = mail;
		this.sort = sort;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", mail="
				+ mail + ", sort=" + sort + "]";
	}
}
