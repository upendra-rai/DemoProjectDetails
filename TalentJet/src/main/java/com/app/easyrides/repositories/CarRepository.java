package com.app.easyrides.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.app.easyrides.entities.Car;
import com.app.easyrides.entities.QCar;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, QuerydslPredicateExecutor<Car>,
		QuerydslBinderCustomizer<QCar> {
	
	 @Query("SELECT s from Car s where s.isActive = true and s.carId =:carId")
	 Optional<Car> findByActiveId(Long carId);

	@Override
	public default void customize(QuerydslBindings bindings, QCar root) {
		bindings.bind(String.class).first((SingleValueBinding<StringPath, String>)StringExpression::containsIgnoreCase);
		bindings.excluding(root.carId);
	}

}
