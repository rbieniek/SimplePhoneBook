package de.urb.sipbook.ejb.idm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jboss.seam.security.annotations.management.EntityType;
import org.jboss.seam.security.annotations.management.IdentityEntity;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

@Entity
@Table(name="IDENTITY_OBJECT_CREDENTIAL")
@IdentityEntity(EntityType.IDENTITY_CREDENTIAL)
public class IdentityObjectCredential implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8652125506746434146L;

	@Id @GeneratedValue @Column(name="ID", nullable=false) private Long id;
	@OneToOne @JoinColumn(name="IDENTITY_OBJECT_ID") private IdentityObject identityObject;
	@ManyToOne @JoinColumn(name="CREDENTIAL_TYPE_ID") @IdentityProperty(PropertyType.TYPE) private IdentityObjectCredentialType type;
	@IdentityProperty(PropertyType.VALUE) @Column(name="VALUE") private String value;
	
	public IdentityObject getIdentityObject() {
		return identityObject;
	}
	
	public void setIdentityObject(IdentityObject identityObject) {
		this.identityObject = identityObject;
	}
	
	public IdentityObjectCredentialType getType() {
		return type;
	}
	
	public void setType(IdentityObjectCredentialType type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}
}
