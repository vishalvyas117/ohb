package com.ohb.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USER_TOKENS")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@NamedQueries({
    @NamedQuery(name = "UserToken.findAll", query = "SELECT u FROM UserToken u"),
    @NamedQuery(name = "UserToken.findByToken", query = "SELECT u FROM UserToken u WHERE u.token = :token"),
    @NamedQuery(name = "UserToken.findByUserId", query = "SELECT u FROM UserToken u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserToken.findByLoginDate", query = "SELECT u FROM UserToken u WHERE u.loginDate = :loginDate"),
    @NamedQuery(name = "UserToken.findByExpirationDate", query = "SELECT u FROM UserToken u WHERE u.expirationDate = :expirationDate")})
public class UserToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @Column(name = "TOKEN")
    private String token;
    
    
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private String userId;
    
    @Basic(optional = false)
    @Column(name = "LOGIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;
    
    @Basic(optional = false)
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

	public UserToken(String token) {
		super();
		this.token = token;
	}

	public UserToken() {
		super();
	}

	public UserToken(String token,String userId, Date loginDate, Date expirationDate) {
		super();
		this.token = token;
		this.userId = userId;
		this.loginDate = loginDate;
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the loginDate
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserToken)) {
            return false;
        }
        UserToken other = (UserToken) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }
    
	
}
