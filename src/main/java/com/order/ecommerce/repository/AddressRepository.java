package com.order.ecommerce.repository;

import com.order.ecommerce.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,String> {
}
