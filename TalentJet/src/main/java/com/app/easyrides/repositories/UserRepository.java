package com.app.easyrides.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.app.easyrides.entities.QUser;
import com.app.easyrides.entities.User;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface UserRepository
		extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

	@Query("select u from User u where u.email=:email")
	public User getUserByUserName(String email);

	public Optional<User> findByToken(String token);
	
	public Optional<User> findByEmail(String email);

	@Override
	public default void customize(QuerydslBindings bindings, QUser root) {
		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
		bindings.excluding(root.userId);
	}

	

}
