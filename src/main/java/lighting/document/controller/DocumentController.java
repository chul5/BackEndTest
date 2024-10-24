package lighting.document.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

	private final AmazonS3 amazonS3Client;

	// 생성자를 통해 AmazonS3 객체 주입

	@PostMapping("/test")
	public String test(@RequestParam("coh") String coh) {
		System.out.println(coh);
		return "redirect:/index.html";
	}
	@PostMapping("/document/multipart-files")
	public void uploadMultipleFile(
			@RequestParam("file") MultipartFile file
	) throws IOException {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());

		PutObjectRequest putObjectRequest = new PutObjectRequest(
				"mybacktestbucket",
				UUID.randomUUID().toString(),  // 고유한 파일명을 위해 UUID 사용
				file.getInputStream(),
				objectMetadata
		);

		amazonS3Client.putObject(putObjectRequest);
	}
}
