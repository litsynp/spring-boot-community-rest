package com.litsynp.community.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.litsynp.community.DataRestApplication;
import com.litsynp.community.rest.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataRestApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
class BoardEventTest {

    private TestRestTemplate testRestTemplate = new TestRestTemplate("litsynp", "test");

    @Test
    void 저장할때_이벤트가_적용되어_생성날짜가_생성되는가() {
        Board createdBoard = createBoard();
        assertNotNull(createdBoard.getCreatedDate());
    }

    @Test
    void 수정할때_이벤트가_적용되어_수정날짜가_생성되는가() {
        Board createdBoard = createBoard();
        Board updatedBoard = updateBoard(createdBoard);
        assertNotNull(updatedBoard.getUpdatedDate());
    }

    private Board createBoard() {
        Board board = Board.builder().title("저장 이벤트 테스트").build();
        return testRestTemplate.postForObject("http://127.0.0.1:8081/api/boards", board,
                Board.class);
    }

    private Board updateBoard(Board createdBoard) {
        String updateUri = "http://127.0.0.1:8081/api/boards/1";
        testRestTemplate.put(updateUri, createdBoard);
        return testRestTemplate.getForObject(updateUri, Board.class);
    }
}
