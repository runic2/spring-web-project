package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor // 생성자 자동 추가 - 생성
public class BoardController {
//	생성자 자동 주입
	private BoardService service;

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}

//	private void deleteFiles(List<BoardAttachVO> attachList) {
//
//		if (attachList == null || attachList.size() == 0) {
//			return;
//		}
//
//		log.info("delete attach files........");
//		log.info(attachList);
//
//		attachList.forEach(attach -> {
//
//			try {
//				Path file = Paths.get(
//						"C:\\upload\\" + attach.getUploadpath() + "\\" + attach.getUuid() + "_" + attach.getFilename());
//
//				Files.deleteIfExists(file);
//
//				if (Files.probeContentType(file).startsWith("image")) {
//
//					Path thumbNail = Paths.get("C:\\upload\\" + attach.getUploadpath() + "\\s_" + attach.getUuid() + "_"
//							+ attach.getFilename());
//
//					Files.delete(thumbNail);
//				}
//
//			} catch (Exception e) {
//				log.error("delete file error : " + e.getMessage());
//			} // end catch
//
//		});// end foreach
//	}

//	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {
//
//		log.info("getAttachList" + bno);
//		return new ResponseEntity<List<BoardAttachVO>>(this.service.getAttachList(bno), HttpStatus.OK);
//	}
//
//	@GetMapping("/list")
//	public void list(Criteria cri, Model model) {// 기본 - controller의 객체는 기본적으로 화면전환 시 전달을 함.
//		log.info("list" + cri);
//		model.addAttribute("list", service.getList(cri));
//		// model.addAttribute("pageMaker", new PageDTO(cri, 123));
//
//		BoardVO board = service.getList(cri).get(1);
//
//		int total = this.service.getTotal(cri);
//
//		log.info("total : " + total);
//
//		model.addAttribute("pageMaker", new PageDTO(cri, total));
//	}
//
	@PostMapping("/register")
//	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {

		log.info("================================");

		log.info("register : " + board);

//		if (board.getAttachList() != null) {
//
//			board.getAttachList().forEach(attach -> log.info(attach));
//		}

		log.info("================================");

		service.register(board);
		// bno select 후, insert

		rttr.addFlashAttribute("result", board.getBno());
		// redirect의 파라미터 지정, 일회성 파라미터

		return "redirect:/board/list";// controller 바로 연결...
	}
//
//	@GetMapping("/register")
//	@PreAuthorize("isAuthenticated()")
//	public void register() {
//
//	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {// @ModelAttribute("cri") Criteria cri => (arg)
		log.info("/get or modify");
		// @ModelAttribute("cri") Criteria cri은 자동으로 지정된 이름의 파라미터로 담음(pageNum, amount)

		model.addAttribute("board", service.get(bno));
	}

//	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {// @ModelAttribute("cri") Criteria cri,

		log.info("modify : " + board);

		if (service.modify(board)) {
			rttr.addAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
// 		+ cri.getListLink(); 해당 page정보(pageNum, amount, type, keyword)

		return "redirect:/board/list";
	}

//	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {// @ModelAttribute("cri") Criteria
																					// cri,

		log.info("remove : " + bno);

//		List<BoardAttachVO> attachList = this.service.getAttachList(bno);

		if (service.remove(bno)) {

			// delete Attach Files
//			deleteFiles(attachList);

			rttr.addAttribute("result", "success");
		}

//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
//		+ cri.getListLink()

		return "redirect:/board/list";
	}
//
//	private String getFolder() {
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date date = new Date();
//
//		String str = sdf.format(date);
//
//		return str.replace("-", File.separator);
//	}
//
//	private boolean checkImageType(File file) {
//
//		try {
//			String contentType = Files.probeContentType(file.toPath());
//
//			return contentType.startsWith("image");
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ResponseEntity<List<BoardAttachVO>> uploadAjaxPost(MultipartFile[] uploadFile) {// uplaod
//
//		List<BoardAttachVO> list = new ArrayList<>();
//
//		String uploadFolder = "C:\\upload";
//
//		String uploadFolderPath = getFolder();
//		// maker folder ---------
//		File uploadPath = new File(uploadFolder, getFolder());// 폴더 생성
//		log.info("upload path : " + uploadPath);
//
//		if (uploadPath.exists() == false) {
//			uploadPath.mkdirs();
//		}
//		// make yyyy/MM/dd folder
//
//		for (MultipartFile multipartFile : uploadFile) {
//
//			BoardAttachVO boardAttachVO = new BoardAttachVO();
//
//			log.info("-------------------------------------");
//			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//			log.info("Upload File Size : " + multipartFile.getSize());
//
//			String uploadFileName = multipartFile.getOriginalFilename();
//
//			// IE has file path
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("//") + 1);
//			log.info("only file name : " + uploadFileName);
//			boardAttachVO.setFilename(uploadFileName);
//
//			// 중복 방지
//			UUID uuid = UUID.randomUUID();
//
//			uploadFileName = uuid.toString() + "_" + uploadFileName;
//
//			// File saveFile = new File(uploadFolder, uploadFileName);
//
//			try {
//				File saveFile = new File(uploadPath, uploadFileName);
//				multipartFile.transferTo(saveFile);// 파일 올림..
//
//				boardAttachVO.setUuid(uuid.toString());
//				boardAttachVO.setUploadpath(uploadFolderPath);
//
//				// check image type file
//				if (checkImageType(saveFile)) {// 이미지면 섬네일 추가
//
//					// attachDTO.setImage(true);
//					boardAttachVO.setFiletype(true);
//					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
//
//					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
//
//					thumbnail.close();
//				}
//
//				// add to List
//				list.add(boardAttachVO);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//
//			} // end catch
//		} // end for
//		return new ResponseEntity<List<BoardAttachVO>>(list, HttpStatus.OK); // json 반환
//	}
//
//	@GetMapping("/display")
//	@ResponseBody
//	public ResponseEntity<byte[]> getFile(String fileName) {// 섬네일 정보 보내기 -> jsp
//
//		log.info("fileName : " + fileName);// 파일 경로가 포함된 이름
//
//		File file = new File("c:\\upload\\" + fileName);
//
//		log.info("file : " + file);// 절대 경로
//
//		ResponseEntity<byte[]> result = null;
//
//		try {
//			HttpHeaders header = new HttpHeaders();
//
//			header.add("Content-Type", Files.probeContentType(file.toPath()));// header에 content type 넣기
//			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//		} catch (IOException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/deleteFile")
//	@ResponseBody
//	public ResponseEntity<String> deleteFile(String fileName, String type) {// 파일 이름과 파일 타입 받기
//
//		log.info("deleteFile : " + fileName);
//
//		File file;
//
//		try {
//			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
//
//			file.delete();// 원본 삭제
//
//			if (type.equals("image")) {// 이미지인 경우, 섬네일도 같이 삭제
//
//				String largeFileName = file.getAbsolutePath().replace("s_", "");
//
//				log.info("largeFileName : " + largeFileName);
//
//				file = new File(largeFileName);
//
//				file.delete();
//
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<String>("delete", HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	@ResponseBody
//	public ResponseEntity<Resource> downloadFile(// 파일 다운로드deleteFile
//			@RequestHeader("User-Agent") String userAgent, String fileName) {// 파일 다운로드 mehtod
//
//		log.info("download file : " + fileName);
//
//		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
//
//		log.info("resource : " + resource);
//
//		if (resource.exists() == false) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		String resourceName = resource.getFilename();
//
//		// remove UUID
//		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
//
//		HttpHeaders headers = new HttpHeaders();
//
//		try {
//
//			String downloadName = null;
//
//			if (userAgent.contains("Trident")) {
//
//				log.info("IE browser");
//
//				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
//
//			} else if (userAgent.contains("Edge")) {
//
//				log.info("Edge browser");
//
//				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
//
//				log.info("Edge name : " + downloadName);
//
//			} else {
//
//				log.info("Chrome browser");
//
//				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
//			}
//
//			headers.add("Content-Disposition", "attachment; fileName=" + downloadName);
//
//		} catch (UnsupportedEncodingException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
//	}
}
