package ar20090702.foundation.bank.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar20090702.foundation.bank.model.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Modifying
	@Query("update Customer c set c.firstName = ?2, c.lastName = ?3, c.address=?4 where c.id = ?1")
	int update(long id, String firstName, String lastName, String address);

}
