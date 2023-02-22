package com.app.easyrides.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.app.easyrides.entities.Car;
import com.app.easyrides.entities.QRentCar;
import com.app.easyrides.entities.RentCar;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface RentCarRepository extends JpaRepository<RentCar, Long>, QuerydslPredicateExecutor<Car>,
		QuerydslBinderCustomizer<QRentCar> {
	
	@Override
	public default void customize(QuerydslBindings bindings, QRentCar root) {
		bindings.bind(String.class).first((SingleValueBinding<StringPath, String>)StringExpression::containsIgnoreCase);
		bindings.excluding(root.rentCarId);
	}

}
