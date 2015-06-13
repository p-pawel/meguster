package meguster.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Answer {

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="answer_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="id", unique=true, nullable=false)	
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
