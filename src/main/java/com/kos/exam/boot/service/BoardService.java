package com.kos.exam.boot.service;

import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.BoardRepository;
import com.kos.exam.boot.vo.Board;

@Service
public class BoardService {

	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
}
