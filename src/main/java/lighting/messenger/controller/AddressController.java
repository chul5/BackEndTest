package lighting.messenger.controller;

import lighting.messenger.dto.EmpDTO;
import lighting.messenger.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AddressController {
	private final AddressService addressService;
	@GetMapping("/api/employees")
	public Mono<List<EmpDTO>> empDTOs() {
		log.info("# webClient 동작 체크..");
		Mono<List<EmpDTO>> employees = addressService.getEmployees();
		return employees;
	}

}
