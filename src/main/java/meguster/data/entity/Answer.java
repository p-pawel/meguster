package meguster.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private MegusterUser user;

	@ManyToOne
	private Art art;
	
	private int value;

	public MegusterUser getUser() {
		return user;
	}

	public void setUser(MegusterUser user) {
		this.user = user;
	}

	public Art getArt() {
		return art;
	}

	public void setArt(Art art) {
		this.art = art;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

}
