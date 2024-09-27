package lighting.messenger.service;

import lighting.messenger.dto.EmpDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AddressService {
	private final WebClient webClient;

	public AddressService() {
		this.webClient = WebClient.builder().baseUrl("http://localhost:9000").build();
	}

	public Mono<List<EmpDTO>> getEmployees() {
		// WebClient를 사용하여 그룹웨어 서버에 비동기 요청
		return webClient.get()
				.uri("/api/v1/intranet/emp/toMessenger") // 그룹웨어 서버의 경로
				.retrieve()
				.bodyToMono(EmpDTO[].class) // EmpDTO 배열을 Mono로 가져옴
				.map(List::of);  // 배열을 List로 변환
	}
}
