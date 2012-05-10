/**
 * 
 */
package de.urb.sipbook.ejb.idm.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rainer
 *
 */
@Entity
@Table(name="IDENTITY_USER")
@NamedQueries({
	@NamedQuery(name=IdentityUser.QUERY_FIND_BY_NAME, 
			query="SELECT u FROM de.urb.sipbook.ejb.idm.model.IdentityUser u WHERE u.name=:" + IdentityUser.QUERY_FIND_BY_NAME_PARAM1)
})
public class IdentityUser implements Serializable {

	public static final String QUERY_FIND_BY_NAME ="findIdentityUserByName";
	public static final String QUERY_FIND_BY_NAME_PARAM1="name";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2699559443360914994L;
	
	private @Id @GeneratedValue @Column(name="ID", nullable=false) Long id;
	private @Column(name="UUID", nullable=false, unique=true, length=32) String uuid;
	private @Column(name="NAME", nullable=false, unique=true, length=16) String name;
	private @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) @JoinColumn(name="IDENTITY_USER_ID", nullable=false) Set<IdentityUserAttribute> attributes;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the attributes
	 */
	public Set<IdentityUserAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Set<IdentityUserAttribute> attributes) {
		this.attributes = attributes;
	}
}
