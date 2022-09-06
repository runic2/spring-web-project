package org.zerock.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

//	localhost:8090/sample/* -> request

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("basic get.............");
	}

	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get ..................");
	}

	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
// 파라미터 자동 수집
//		....sample?name=AAA&age=10
		log.info(dto);
		return "ex01";
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
//		기본형 수집
//		....sample?name=AAA&age=10
		return "ex02";
	}

	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
//		...?list[0].name=AAA&list[1].name=BBB
		return "ex02Bean";
	}

	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
//		...?title=test&dueDate=2018-01-01
		return "ex03";

	}

	@GetMapping("ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
//		화면 이동 시, 객체는 가지만, 기본형 데이터는 가지 못함..
//		@ModelAttribute("")를 붙여 기본형 데이터도 보냄..
		return "/sample/ex04";
	}

//	RedirectAttribute
	@RequestMapping(value = "/ex01Re", method = { RequestMethod.GET, RequestMethod.POST })
	public String ex01_Redirect(SampleDTO dto, RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", "AAA");
		rttr.addFlashAttribute("age", 10);
//		response.sendRedirect("...?name=AAA&age=10");
		return "redirect:/";
	}

	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05.... request...");
	}

	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
//		@ResponseBody -> JSON Type으로 반환
		log.info("/ex06....");

		SampleDTO dto = new SampleDTO();
		dto.setName("AAA");
		dto.setAge(10);

		return dto;
	}

	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
//		헤더 정보 전달
		log.info("/ex07.........");

//		{"name" : "홍길동"}
		String msg = "{\"name\": \"홍길동\"}";

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "appliaction/json;charset=UTF-8");

		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
}
