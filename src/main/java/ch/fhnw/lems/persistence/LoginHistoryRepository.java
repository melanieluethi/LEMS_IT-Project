package ch.fhnw.lems.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.fhnw.lems.dto.LoginHistory;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {
		@Query(value="select id from login_history where user_id =:user_id order by login DESC LIMIT 1", nativeQuery = true)
		Integer lastUserLogin(@Param("user_id") Long user_id);
}