
package org.com.customer.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.entities
 * @date    : 21 mai 2023 13:28:22
 */
@Entity(name = "Customer")
@Table(name = "customer")
@Data
public class CustomerEntity implements Serializable
{
   @java.io.Serial
   private static final long serialVersionUID = -2743626572947459832L;

   @Id
   @Column(name = "customer_id", unique = true)
   private Integer customerId;
   
   @Column(name = "first_name", length = 60, nullable = false)
   @NotEmpty
   //@Min(value = 4)
   private String  firstName;
   
   @Column(name = "middle_name", length = 60)
   private String  middleName;
   
   @Column(name = "last_name", length = 60, nullable = false)
   @NotEmpty
   //@Min(value = 4)
   private String  lastName;
   
   @Column(name = "suffix", length = 6)
   private String  suffix;
   
   @Column(name = "email", length = 60, unique = true,  nullable = false)
   @Email
   //@Min(value = 10)
   private String  email;
   
   @Column(name = "phone", length = 15, unique = true,  nullable = false)
   @NotEmpty
   //@Min(value = 10)
   private String  phone;

/**
 * @return the customerId
 */
public Integer getCustomerId() {
	return customerId;
}

/**
 * @param customerId the customerId to set
 */
public void setCustomerId(Integer customerId) {
	this.customerId = customerId;
}

/**
 * @return the firstName
 */
public String getFirstName() {
	return firstName;
}

/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * @return the middleName
 */
public String getMiddleName() {
	return middleName;
}

/**
 * @param middleName the middleName to set
 */
public void setMiddleName(String middleName) {
	this.middleName = middleName;
}

/**
 * @return the lastName
 */
public String getLastName() {
	return lastName;
}

/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * @return the suffix
 */
public String getSuffix() {
	return suffix;
}

/**
 * @param suffix the suffix to set
 */
public void setSuffix(String suffix) {
	this.suffix = suffix;
}

/**
 * @return the email
 */
public String getEmail() {
	return email;
}

/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * @return the phone
 */
public String getPhone() {
	return phone;
}

/**
 * @param phone the phone to set
 */
public void setPhone(String phone) {
	this.phone = phone;
}
   
   
}