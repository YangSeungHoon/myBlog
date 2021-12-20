package com.sheep.sh.myblog.repository;


import com.sheep.sh.myblog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
