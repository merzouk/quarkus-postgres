
package org.com.customer.daos;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

import org.com.customer.entities.CustomerEntity;

/**
 * 
 * A Renseigner.
 * 
 * 
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.daos
 * @date    : 21 mai 2023 13:28:36
 */
@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<CustomerEntity, Integer>
{
}