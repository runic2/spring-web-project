package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // bean 설정을 불러옴.
@Log4j()
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Test
	public void testGetList() {
		this.mapper.getList().forEach(board -> log.info(board));

	}

	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");

		this.mapper.insert(board);

		log.info(board);
	}

	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");

		this.mapper.insertSelectKey(board);

		log.info(board);
	}

	@Test
	public void testRead() {
//		5L = Long type
		BoardVO board = this.mapper.read(5L);

		log.info(board);
	}

	@Test
	public void testDelete() {
		log.info("Delete count : " + this.mapper.delete(3L));
	}

	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");

		int count = this.mapper.update(board);
		log.info("update count : " + count);

	}

//	@Test
//	public void testPasing () {
//		
//		Criteria cri = new Criteria();
//		cri.setPageNum(1);
//		cri.setAmount(10);
//		List<BoardVO> list = this.mapper.getListWithPaging(cri);
//		list.forEach(board -> log.info(board.getBno()));
//	}

//	@Test
//	public void testSearch () {
//		Criteria cri = new Criteria();
//		cri.setKeyword("새로");
//		cri.setType("TC");
//		
//		List<BoardVO> list = this.mapper.getListWithPaging(cri);
//		
//		list.forEach(board -> log.info(board));
//	}

}
