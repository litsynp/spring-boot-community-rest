package com.litsynp.community.rest.repository;

import com.litsynp.community.rest.domain.Board;
import com.litsynp.community.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByUser(User user);
}
