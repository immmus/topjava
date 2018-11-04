package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> findAll(@Param("userId") int userId);

    @Override
    @Transactional
    <S extends Meal> S save(S s);

    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.user WHERE m.id=:id and m.user.id=:userId")
    Optional<Meal> findById(@Param("id") int id, @Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime" +
            " BETWEEN :startDate and :endDate order by m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate")LocalDateTime startDate, @Param("endDate")LocalDateTime endDate, @Param("userId") int userId);
}
