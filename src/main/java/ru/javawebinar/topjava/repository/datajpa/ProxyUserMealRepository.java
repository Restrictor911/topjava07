package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Restrictor on 07.07.2016.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {
    UserMeal findByIdAndUserId(Integer id, Integer userId);

    List<UserMeal> findAllByUserId(Sort sort, Integer userId);

    List<UserMeal> findAllByUserIdAndDateTimeBetween(Sort sort, Integer userId,
                                                     LocalDateTime startDate,
                                                     LocalDateTime endDate);

    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
    int delete(@Param("id") int id,
               @Param("userId") int userId);
}
