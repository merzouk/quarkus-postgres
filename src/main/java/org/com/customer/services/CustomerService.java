
package org.com.customer.services;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import io.quarkus.hibernate.orm.panache.Panache;
import org.com.customer.daos.CustomerRepository;
import org.com.customer.entities.CustomerEntity;
import org.com.customer.exceptions.CustomerException;
import org.com.customer.models.Customer;
import org.com.customer.tools.CustomerMapper;
import org.com.customer.tools.Tools;
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
        return this.customerMapper.toListModel( customerRepository.findAll().list() );
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
            logger.error("Find Customer by customerId not valid : "+customerId);
            throw new CustomerException( "Find Customer by customerId not valid : "+customerId );
        }
        return customerRepository.findByIdOptional( customerId ).map( customerMapper::toModel);
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
            logger.error("Delete Customer by customerId not valid : "+customerId);
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
            logger.error("Find Customer List by firstname not valid : "+firstname);
            throw new CustomerException( "Find Customer List by firstname not valid : "+firstname );
        }
        return this.customerMapper.toListModel( customerRepository.list( "first_name", firstname ) );
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
            logger.error("Find Customer List by firstname: "+firstname +"  And lastname: "+lastname+" not valid");
            throw new CustomerException( "Find Customer List by firstname: "+firstname +"  And lastname: "+lastname+" not valid");
        }
        String query = "FROM Customer c WHERE c.firstName =: first_name and c.lastName =: last_name";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "first_name", firstname );
        params.put( "last_name",  lastname );
        return this.customerMapper.toListModel( customerRepository.list( query, params ) );
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
            logger.error("Find Customer List by lastname not valid : "+lastname);
            throw new CustomerException( "Find Customer List by lastname not valid : "+lastname );
        }
        return this.customerMapper.toListModel( customerRepository.list( "last_name", lastname ) );
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
            logger.error("Find Customer by email not valid : "+email);
            throw new CustomerException( "Find Customer by email not valid : "+email );
        }
        if(!Tools.validateEmail(email))
        {
            logger.error("email is not valid : "+email);
            throw new CustomerException( "email is not valid : "+email );
        }
        return customerRepository.find( "email", email ).firstResultOptional().map( customerMapper::toModel);
    }

    @Transactional(rollbackOn = Exception.class)
    public void save( @Valid Customer customer )
    {
        if( Objects.isNull( customer ) )
        {
            logger.error("Saving new Customer not valid ");
            throw new CustomerException( "Saving new Customer not valid" );
        }
        if(!Tools.validateEmail(customer.getEmail()))
        {
            logger.error("email is not valid : "+customer.getEmail());
            throw new CustomerException( "email is not valid : "+customer.getEmail() );
        }
        if( Objects.isNull( customer.getFirstName() ) )
        {
            logger.error("Saving new Customer not valid for firstname "+customer.getFirstName());
            throw new CustomerException( "Saving new Customer not valid for firstname "+customer.getFirstName() );
        }
        if( Objects.isNull( customer.getLastName() ) )
        {
            logger.error("Saving new Customer not valid for lastname "+customer.getLastName());
            throw new CustomerException( "Saving new Customer not valid for lastname "+customer.getLastName() );
        }
        if( Objects.isNull( customer.getPhone() ) )
        {
            logger.error("Saving new Customer not valid for Phone "+customer.getPhone());
            throw new CustomerException( "Saving new Customer not valid for Phone "+customer.getPhone() );
        }
        if( !Tools.isNumeric(customer.getPhone() ) )
        {
            logger.error("Saving new Customer not valid for Phone \""+customer.getPhone() +"\" Phone is not numeric value");
            throw new CustomerException( "Saving new Customer not valid for Phone \""+customer.getPhone() +"\" Phone is not numeric value");
        }
        logger.debug( "Saving Customer : {}", customer.toString() );
        CustomerEntity entity = customerMapper.toEntity( customer );
        entity.setCustomerId(getMaxCustomerId() + 1);
        customerRepository.persist( entity );
        customerMapper.updateModelFromEntity( entity, customer );
    }

    @Transactional(rollbackOn = Exception.class)
    public void update( @Valid Customer customer )
    {
        if( Objects.isNull( customer ) )
        {
            logger.error("updating Customer not valid ");
            throw new CustomerException( "updating Customer not valid" );
        }
        logger.debug( "Updating Customer : {}", customer.toString() );
        if( Objects.isNull( customer.getCustomerId() ) )
        {
            logger.error("Customer does not have a customerId");
            throw new CustomerException( "Customer does not have a customerId" );
        }
        if(!Tools.validateEmail(customer.getEmail()))
        {
            logger.error("email is not valid : "+customer.getEmail());
            throw new CustomerException( "email is not valid : "+customer.getEmail() );
        }
        CustomerEntity entity = customerRepository.findByIdOptional( customer.getCustomerId() )
                .orElseThrow( () -> new CustomerException( "No Customer found for customerId[%s]", customer.getCustomerId() ) );
        customerMapper.updateEntityFromModel( customer, entity );
        customerRepository.persist( entity );
        customerMapper.updateModelFromEntity( entity, customer );
    }

    private Integer getMaxCustomerId()
    {
        return Panache.getEntityManager()
                .createQuery( "SELECT max(c.customerId) FROM Customer c", Integer.class )
                .getSingleResult();
    }
}