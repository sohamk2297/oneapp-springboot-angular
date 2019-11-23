package ecommerceapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import ecommerceapp.models.Details;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DetailField {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String field;
	private String value;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Details details;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
