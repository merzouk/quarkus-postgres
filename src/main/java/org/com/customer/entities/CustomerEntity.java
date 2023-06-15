
package org.com.customer.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
}