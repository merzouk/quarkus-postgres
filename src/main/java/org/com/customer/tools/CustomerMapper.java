
package org.com.customer.tools;

import org.com.customer.entities.CustomerEntity;
import org.com.customer.models.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.tools
 * @date    : 21 mai 2023 13:29:32
 */
@Mapper(componentModel = "cdi")
@SuppressWarnings("all")
public interface CustomerMapper
{
   List<Customer> toListModel(List<CustomerEntity> entities );
   
   Customer toModel(CustomerEntity entity);
   
   @InheritInverseConfiguration(name = "toModel")
   CustomerEntity toEntity( Customer domain );
   
   void updateEntityFromModel(Customer domain, @MappingTarget CustomerEntity entity );
   
   void updateModelFromEntity(CustomerEntity entity, @MappingTarget Customer domain );
}