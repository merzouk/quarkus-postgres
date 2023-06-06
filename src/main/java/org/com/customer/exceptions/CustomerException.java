
package org.com.customer.exceptions;
/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.exceptions
 * @date    : 21 mai 2023 13:28:11
 */
public class CustomerException extends RuntimeException
{
   private static final long serialVersionUID = -1234567892321459821L;
   /**
    * 
    * @param message
    */
   public CustomerException( String message )
   {
      super( message );
   }
   
   /**
    * 
    * @param format
    * @param objects
    */
   public CustomerException( String format, Object... objects )
   {
      super( String.format( format, objects ) );
   }
}