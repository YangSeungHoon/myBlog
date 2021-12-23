package com.sheep.sh.myblog.repository;

import com.sheep.sh.myblog.dto.ReplySaveRequestDto;
import com.sheep.sh.myblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Query(value="INSERT INTO reply(userId,boardId,content,createDate) VALUES(?1,?2,?3,now())", nativeQuery = true)
    int commentSave(Long userId,Long boardId,String content); //업데이트된 행의 갯수가 리턴된다.
}
