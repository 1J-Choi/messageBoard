package com.example.messageBoard.repository;

import com.example.messageBoard.entity.MainText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MainTextRepository extends JpaRepository<MainText, Long> {
    @Query("SELECT m FROM MainText AS m")
    MainText findJustOne();
}
