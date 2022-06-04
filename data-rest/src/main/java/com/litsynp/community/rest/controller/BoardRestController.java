package com.litsynp.community.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.litsynp.community.rest.domain.Board;
import com.litsynp.community.rest.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class BoardRestController {

    private BoardRepository boardRepository;

    public BoardRestController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/boards")
    public @ResponseBody PagedModel<Board> simpleBoard(@PageableDefault Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);

        PageMetadata pageMetadata = new PageMetadata(pageable.getPageSize(), boardList.getNumber(),
                boardList.getTotalElements());
        PagedModel<Board> model = PagedModel.of(boardList.getContent(), pageMetadata);
        model.add(linkTo(methodOn(BoardRestController.class).simpleBoard(pageable)).withSelfRel());
        return model;
    }
}
