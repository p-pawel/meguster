package meguster.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Art {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
