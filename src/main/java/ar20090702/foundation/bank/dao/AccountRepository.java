package ar20090702.foundation.bank.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar20090702.foundation.bank.model.Account;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByNumber(long accountNumber);

	@Modifying
	@Query("update Account a set a.balance = ?2 where a.number = ?1")
	int updateByNumber(long accountNumber, double newBalance);

}
