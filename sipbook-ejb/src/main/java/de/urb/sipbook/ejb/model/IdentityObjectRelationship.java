package de.urb.sipbook.ejb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

@Entity
@Table(name="IDENTITY_OBJECT_RELATIONSHIP")
public class IdentityObjectRelationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1373225670568016950L;

	@Id @GeneratedValue @Column(name="ID") private Long id;
	@IdentityProperty(PropertyType.NAME) @Column(name="NAME") private String name;
	@ManyToOne @JoinColumn(name="RELATIONSHIP_TYPE_ID") @IdentityProperty(PropertyType.TYPE) private IdentityObjectRelationshipType relationshipType;
	@ManyToOne @JoinColumn(name="FROM_IDENTITY_ID") @IdentityProperty(PropertyType.RELATIONSHIP_FROM) private IdentityObject from;
	@ManyToOne @JoinColumn(name="TO_IDENTITY_ID") @IdentityProperty(PropertyType.RELATIONSHIP_TO) private IdentityObject to;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public IdentityObjectRelationshipType getRelationshipType() {
		return relationshipType;
	}
	
	public void setRelationshipType(IdentityObjectRelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	public IdentityObject getFrom() {
		return from;
	}
	
	public void setFrom(IdentityObject from) {
		this.from = from;
	}
	
	public IdentityObject getTo() {
		return to;
	}
	
	public void setTo(IdentityObject to) {
		this.to = to;
	}
	
	public Long getId() {
		return id;
	}
}
