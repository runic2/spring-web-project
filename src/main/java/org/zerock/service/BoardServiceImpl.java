package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor // 생성자 자동 생성
public class BoardServiceImpl implements BoardService {
//	spring 4.3이상 자동 생성자 주입
	private BoardMapper mapper;

//	@Setter(onMethod_ = @Autowired)
//	private BoardAttachMapper attachMapper;

//	@Override
//	public List<BoardAttachVO> getAttachList(Long bno) {
//		// TODO Auto-generated method stub
//
//		log.info("get Attach list by bno" + bno);
//		return this.attachMapper.findByBno(bno);
//	}

//	@Transactional
	@Override
	public void register(BoardVO board) {
		// TODO Auto-generated method stub
		log.info("register....." + board);
		this.mapper.insertSelectKey(board);

//		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
//			return;
//		}
//
//		board.getAttachList().forEach(attach -> {
//
//			attach.setBno(board.getBno());
//			attachMapper.insert(attach);
//		});
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		log.info("get.............." + bno);
		return this.mapper.read(bno);
	}

//	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		// TODO Auto-generated method stub

		log.info("modify : " + board);

//		this.attachMapper.deleteAll(board.getBno());

		boolean modifyResult = this.mapper.update(board) == 1;

//		if (modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
//			board.getAttachList().forEach(attach -> {
//
//				attach.setBno(board.getBno());
//				this.attachMapper.insert(attach);
//			});
//		}
		return modifyResult;
	}

//	@Transactional
	@Override
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub

		log.info("remove : " + bno);

//		this.attachMapper.deleteAll(bno);

		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		log.info("getList...................");
		return this.mapper.getList();
	}

//	@Override
//	public List<BoardVO> getList(Criteria cri) {
//		// TODO Auto-generated method stub
//		log.info("getList with critreia" + cri);
//		return this.mapper.getListWithPaging(cri);
//	}
//
//	@Override
//	public int getTotal(Criteria cri) {
//		// TODO Auto-generated method stub
//		log.info("get total count");
//		return this.mapper.getTotalCount(cri);
//	}
}
