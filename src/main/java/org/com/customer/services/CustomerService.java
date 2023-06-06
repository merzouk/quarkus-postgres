
package org.com.customer.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.com.customer.daos.CustomerRepository;
import org.com.customer.entities.CustomerEntity;
import org.com.customer.exceptions.CustomerException;
import org.com.customer.models.Customer;
import org.com.customer.tools.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.services
 * @date    : 21 mai 2023 13:29:01
 */
@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CustomerService
{
   @Inject
   CustomerRepository  customerRepository;
   
   @Inject
   CustomerMapper      customerMapper;
   
   private static final Logger logger = LoggerFactory.getLogger( CustomerService.class );
   /**
    * 
    * @return
    */
   public List<Customer> findAll()
   {
      return this.customerMapper.toDomainList( customerRepository.findAll().list() );
   }
   
   /**
    * 
    * @param customerId
    * @return
    */
   public Optional<Customer> findById( @NonNull Integer customerId )
   {
      if( Objects.isNull( customerId ) )
      {
         throw new CustomerException( "Find Customer by customerId not valid : "+customerId );
      }
      return customerRepository.findByIdOptional( customerId ).map( customerMapper::toDomain );
   }
   
   /**
    * 
    * @param customerId
    * @return
    */
   @Transactional(rollbackOn = Exception.class)
   public boolean deleteById( @NonNull Integer customerId )
   {
      if( Objects.isNull( customerId ) )
      {
         throw new CustomerException( "Delete Customer by customerId not valid : "+customerId );
      }
      return customerRepository.deleteById( customerId );
   }
   
   /**
    * 
    * @param firstname
    * @return
    */
   public List<Customer> findByFirstName( @NonNull String firstname )
   {
      if( Objects.isNull( firstname ) )
      {
         throw new CustomerException( "Find Customer List by firstname not valid : "+firstname );
      }
      return this.customerMapper.toDomainList( customerRepository.list( "first_name", firstname ) );
   }
   
  /**
   * 
   * @param firstname
   * @param lastname
   * @return
   */
   public List<Customer> findByFirstNameAndLastName( @NonNull String firstname , @NonNull String lastname)
   {
      if( Objects.isNull( firstname ) || Objects.isNull( lastname ))
      {
         throw new CustomerException( "Find Customer List by firstname: "+firstname +"  And lastname: "+lastname+" not valid");
      }
      String query = "FROM Customer c WHERE c.firstName =: first_name and c.lastName =: last_name"; 
      Map<String, Object> params = new HashMap<String, Object>();
      params.put( "first_name", firstname );
      params.put( "last_name",  lastname );
      return this.customerMapper.toDomainList( customerRepository.list( query, params ) );
   }
   
   /**
   * 
   * @param lastname
   * @return
   */
   public List<Customer> findByLastName( @NonNull String lastname )
   {
      if( Objects.isNull( lastname ) )
      {
         throw new CustomerException( "Find Customer List by lastname not valid : "+lastname );
      }
      return this.customerMapper.toDomainList( customerRepository.list( "last_name", lastname ) );
   }
   
   /**
   * 
   * @param email
   * @return
   */
   public Optional<Customer> findByEmail( @NonNull String email )
   {
      if( Objects.isNull( email ) )
      {
         throw new CustomerException( "Find Customer by email not valid : "+email );
      }
      return customerRepository.find( "email", email ).firstResultOptional().map( customerMapper::toDomain );
   }
   
   @Transactional(rollbackOn = Exception.class)
   public void save( @Valid Customer customer )
   {
      if( Objects.isNull( customer ) )
      {
         throw new CustomerException( "Save new Customer not valid" );
      }
      logger.debug( "Saving Customer : {}", customer.toString() );
      CustomerEntity entity = customerMapper.toEntity( customer );
      customerRepository.persist( entity );
      customerMapper.updateDomainFromEntity( entity, customer );
   }
   
   @Transactional(rollbackOn = Exception.class)
   public void update( @Valid Customer customer )
   {
      logger.debug( "Updating Customer : {}", customer.toString() );
      if( Objects.isNull( customer.getCustomerId() ) )
      {
         throw new CustomerException( "Customer does not have a customerId" );
      }
      CustomerEntity entity = customerRepository.findByIdOptional( customer.getCustomerId() )
                                                .orElseThrow( () -> new CustomerException( "No Customer found for customerId[%s]", customer.getCustomerId() ) );
      customerMapper.updateEntityFromDomain( customer, entity );
      customerRepository.persist( entity );
      customerMapper.updateDomainFromEntity( entity, customer );
   }
}