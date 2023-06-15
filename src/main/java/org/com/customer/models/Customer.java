
package org.com.customer.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.models
 * @date    : 21 mai 2023 13:28:46
 */
@Data
public class Customer implements Serializable
{
   
   private static final long serialVersionUID = 6490814933221125505L;

   //@NotEmpty
   private Integer customerId;
   
   @NotEmpty
   //@Min(value = 4)
   private String  firstName;
   
   private String  middleName;
   
   @NotEmpty
   //@Min(value = 4)
   private String  lastName;
   
   private String  suffix;
   
   @Email
   @NotEmpty
   //@Min(value = 10)
   private String  email;
   
   @NotEmpty
   private String  phone;
   /**
    * 
    * @return
    */
   public Integer getCustomerId()
   {
      return customerId;
   }
   
   /**
    * 
    * @param customerId
    */
   public void setCustomerId( Integer customerId )
   {
      this.customerId = customerId;
   }
   
   public String getFirstName()
   {
      return firstName;
   }
   
   public void setFirstName( String firstName )
   {
      this.firstName = firstName;
   }
   
   public String getMiddleName()
   {
      return middleName;
   }
   
   public void setMiddleName( String middleName )
   {
      this.middleName = middleName;
   }
   
   public String getLastName()
   {
      return lastName;
   }
   
   public void setLastName( String lastName )
   {
      this.lastName = lastName;
   }
   
   public String getSuffix()
   {
      return suffix;
   }
   
   public void setSuffix( String suffix )
   {
      this.suffix = suffix;
   }
   
   public String getEmail()
   {
      return email;
   }
   
   public void setEmail( String email )
   {
      this.email = email;
   }
   
   public String getPhone()
   {
      return phone;
   }
   
   public void setPhone( String phone )
   {
      this.phone = phone;
   }
   
   @Override
   public int hashCode()
   {
      return Objects.hash( customerId, email, firstName, lastName, middleName, phone, suffix );
   }
   
   @Override
   public boolean equals( Object obj )
   {
      //if( this == obj ) return true;
      if( obj == null ) return false;
      if( getClass() != obj.getClass() ) return false;
      Customer other = (Customer) obj;
      return Objects.equals( 
                               customerId, other.customerId ) 
                               && Objects.equals( email, other.email ) 
                               && Objects.equals( firstName, other.firstName ) 
                               && Objects.equals( lastName, other.lastName ) 
                               && Objects.equals( middleName, other.middleName ) 
                               && Objects.equals( phone, other.phone ) 
                               && Objects.equals( suffix, other.suffix 
                            );
   }
   
   @Override
   public String toString()
   {
      return "Customer [customerId=" + customerId + ", "
                     + "firstName=" + firstName 
                     + ", middleName=" + middleName 
                     + ", lastName=" + lastName 
                     + ", suffix=" + suffix 
                     + ", email=" + email 
                     + ", phone=" + phone 
                     + "]";
   }
}