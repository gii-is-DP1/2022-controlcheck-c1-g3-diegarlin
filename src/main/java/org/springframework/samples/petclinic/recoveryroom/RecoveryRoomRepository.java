package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer> {
    List<RecoveryRoom> findAll();
    Optional<RecoveryRoom> findById(int id);
    RecoveryRoom save(RecoveryRoom p);
    @Query("select u from RecoveryRoomType u")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();

    @Query("SELECT u from RecoveryRoomType u where u.name = ?1")
    RecoveryRoomType getRecoveryRoomType(String name);

}
