package meguster.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Art {

	
	@Id 
	@SequenceGenerator(name="pk_sequence",sequenceName="art_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="id", unique=true, nullable=false)	
	private Long id;

	private ArtType type;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public enum ArtType {
		IMAGE, EMBED
	}

	public Long getId() {
		return id;
	}

	public ArtType getType() {
		return type;
	}

	public void setType(ArtType type) {
		this.type = type;
	};

}
