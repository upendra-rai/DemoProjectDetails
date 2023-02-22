package com.app.easyrides.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.app.easyrides.entities.Address;
import com.app.easyrides.entities.Customer;
import com.app.easyrides.entities.QAddress;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, QuerydslPredicateExecutor<Address>,
		QuerydslBinderCustomizer<QAddress> {

	@Override
	public default void customize(QuerydslBindings bindings, QAddress root) {
		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
		bindings.excluding(root.addressId);
	}

}
