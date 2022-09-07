package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // Test for Controller
@WebAppConfiguration // was 없이 controller test
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) // bean 설정을 불러옴.//servlet-context.xml를 씀
@Log4j()
public class BoardControllerTests {

//	controller Test

	// return String -->
	// movkMvc.perform(MockMvcRequestBuilders.post[get]("url").param("name","data").param...).andReturn().getModelAndView().getViewName();

	// return Object -->
	// movkMvc.perform(MockMvcRequestBuilders.post[get]("url").param("name","data").param...).andReturn().getModelAndView().getModelMap();
	// -> 반환된 Model객체 리턴

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;

	private MockMvc movkMvc;
	// 더미 mvc

	// 적용된 모든 메서드 전에 매번 실행하는 메서드
	@Before
	public void setup() {
		this.movkMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}// MockMvc 생성

	@Test
	public void testList() throws Exception {
//		log.info(this.movkMvc
//				.perform(MockMvcRequestBuilders.get("/board/list").param("pageNum", "2").param("amount", "50"))
//				.andReturn().getModelAndView().getModelMap());
		log.info(this.movkMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView()
				.getModelMap());
	}

	@Test
	public void testRegister() throws Exception {
		String resultPage = this.movkMvc
				.perform(MockMvcRequestBuilders.post("/board/register").param("title", "테스트 새글 제목")
						.param("content", "테스트 새글 내용").param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

	@Test
	public void testGet() throws Exception {
		log.info(this.movkMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "4")).andReturn()
				.getModelAndView().getModelMap());
	}

	@Test
	public void testModify() throws Exception {
		String resultPage = this.movkMvc
				.perform(MockMvcRequestBuilders.post("/board/modify").param("bno", "1").param("title", "수정된 테스트 새글 제목")
						.param("content", "수정된 테스트 새글 내용").param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

	@Test
	public void testRemove() throws Exception {
		String resultPage = this.movkMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", "4"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}
}
