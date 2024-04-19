package com.example.OX.repository;

import com.example.OX.entitiy.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz , Long> {

}
