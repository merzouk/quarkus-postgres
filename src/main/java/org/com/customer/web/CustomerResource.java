package org.com.customer.web;

import java.net.URI;
import java.util.Objects;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.com.customer.exceptions.CustomerException;
import org.com.customer.models.Customer;
import org.com.customer.services.CustomerService;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * A Renseigner.
 * @author  : admin
 * @project : customer-api-0.0.1
 * @package : org.com.customer.web
 * @date    : 21 mai 2023 13:29:23
 */
@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "customer", description = "Customer Operations")
@AllArgsConstructor
@Slf4j
public class CustomerResource 
{

    @Inject
    CustomerService customerService;

    /**
     * 
     * @return Response
     */
    @GET
    @Path("/all")
    @APIResponse(
            responseCode = "200",
            description = "Get All Customers",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Customer.class)
            )
    )
    public Response allCustomers() 
    {
        return Response.ok(customerService.findAll()).build();
    }

    /**
     * 
     * @param customerId : id of entity
     * @return Response
     */
    @GET
    @Path("/byId/{customerId}")
    @APIResponse(
            responseCode = "200",
            description = "Get Customer by customerId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response customerById(@Parameter(name = "customerId", required = true) 
                                 @PathParam("customerId") Integer customerId)
    {
        return customerService.findById(customerId)
                              .map(customer -> Response.ok(customer).build())
                              .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * 
     * @param customerId : identity of entity
     * @return Response
     */
    @DELETE
    @Path("/delete/{customerId}")
    @APIResponse(
            responseCode = "200",
            description = "Delete Customer by customerId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response deleteCustomerById(@Parameter(name = "customerId", required = true) 
                                       @PathParam("customerId") Integer customerId)
    {
       return Response.ok(customerService.deleteById(customerId))
                      .build();
    }
    
    /**
     * 
     * @param firstname : firstname of customer
     * @return Response
     */
    @GET
    @Path("/firstname/{first_name}")
    @APIResponse(
            responseCode = "200",
            description = "Get Customer by firstname",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for firstname",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response customerByFirstname(@Parameter(name = "firstname", required = true) 
                                        @PathParam("first_name") String firstname)
    {
       return Response.ok(customerService.findByFirstName(firstname))
                      .build();
    }
    
    /**
     * 
     * @param lastname : lastname of customer
     * @return Response
     */
    @GET
    @Path("/lastname/{last_name}")
    @APIResponse(
            responseCode = "200",
            description = "Get Customer by lastname",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for lastname",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response customerByLastname(@Parameter(name = "lastname", required = true) 
                                       @PathParam("last_name") String lastname)
    {
       return Response.ok(customerService.findByLastName(lastname))
                      .build();
    }
    
    /**
     * 
     * @param firstname : firstname of customer
     * @param lastname : lastname of customer
     * @return Response
     */
    @GET
    @Path("/identity/{first_name}/{last_name}")
    @APIResponse(
            responseCode = "200",
            description = "Customer list by firstname and lastname",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for firstname and lastname",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response customersByFirstnameAndLastname(@Parameter(name = "firstname", required = true)
                                                    @PathParam("first_name") String firstname,
                                                    @Parameter(name = "lastname", required = true)
                                                    @PathParam("last_name") String lastname)
    {
       return Response.ok(customerService.findByFirstNameAndLastName(firstname, lastname))
                      .build();
    }
    
    /**
     * 
     * @param email : email of customer
     * @return Respoonse
     */
    @GET
    @Path("/email/{email}")
    @APIResponse(
            responseCode = "200",
            description = "Get Customer by email",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for email",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response customerByEmail(@Parameter(name = "email", required = true) 
                               @PathParam("email") String email) 
    {
       return Response.ok(customerService.findByEmail(email))
                      .build();
    }
    
    /**
     * 
     * @param customer : customer to add
     * @param uriInfo : verify
     * @return Response
     */
    @POST
    @Path("/add")
    @APIResponse(
            responseCode = "201",
            description = "Customer Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Customer",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Customer already exists for customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response postCustomer(@NotNull @Valid Customer customer, 
                                 @Context UriInfo uriInfo)
    {
        customerService.save(customer);
        URI uri = uriInfo.getAbsolutePathBuilder()
                         .path(Integer.toString(customer.getCustomerId()))
                         .build();
        return Response.created(uri)
                       .entity(customer)
                       .build();
    }
    
    /**
     * 
     * @param customerId : identity of entity
     * @param customer : customer to updated
     * @return Response
     */
    @PUT
    @Path("/update/{customerId}")
    @APIResponse(
            responseCode = "204",
            description = "Customer updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Customer",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Customer object does not have customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Path variable customerId does not match Customer.customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Customer found for customerId provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )    
    public Response putCustomer(@Parameter(name = "customerId", required = true) 
                                @PathParam("customerId") Integer customerId,
                                @NotNull @Valid Customer customer)
    {
        if (!Objects.equals(customerId, customer.getCustomerId())) 
        {
            throw new CustomerException("Path variable customerId does not match Customer.customerId "+customerId);
        }
        if(Objects.isNull(customer.getCustomerId()))
        {
            throw new CustomerException("customerId does not valid "+customer.getCustomerId());
        }
        customerService.update(customer);
        return Response.status(Response.Status.NO_CONTENT)
                       .build();
    }
}