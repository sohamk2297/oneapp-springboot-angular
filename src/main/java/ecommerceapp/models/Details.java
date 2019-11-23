package ecommerceapp.models;

import java.util.Collection;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Details {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "detail_field_id")
	private Collection<DetailField> detailFields;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "details")
	@JoinColumn(name = "product_id")
	private Product product;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<DetailField> getDetailFields() {
		return detailFields;
	}
	public void setDetailFields(Collection<DetailField> detailFields) {
		this.detailFields = detailFields;
	}
	
	/*
	 * public Map<String, String> getDetailsMap() { return detailsMap; } public void
	 * setDetailsMap(Map<String, String> detailsMap) { this.detailsMap = detailsMap;
	 * }
	 */	 
	
	


}
