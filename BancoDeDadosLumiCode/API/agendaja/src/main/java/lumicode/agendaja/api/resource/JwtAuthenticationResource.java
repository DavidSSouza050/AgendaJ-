package lumicode.agendaja.api.resource;


import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.jwtme.security.JwtTokenUtill;
import lumicode.agendaja.api.model.Cliente;
import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.model.JWTRequest;
import lumicode.agendaja.api.model.JWTResponse;
import lumicode.agendaja.api.repository.ClienteRepository;
import lumicode.agendaja.api.repository.EstabelecimentoRepository;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = {"eventBusIndex", "verbose"})
public class JwtAuthenticationResource {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Autowired
	ClienteRepository clienteRepository;
		
	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;
	
	@PostMapping("/login/cliente")
	public ResponseEntity<?> createAuthenticationTokenConsumidor(@RequestBody JWTRequest authenticationRequest) throws Exception {
		final Cliente cliente = clienteRepository.entrar(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		if (cliente != null) {
			final String token = jwtTokenUtil.generateTokenConsumidor(cliente);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");

	}
	
	@PostMapping("/login/estabelecimento")
	public ResponseEntity<?> createAuthenticationTokenRestaurante(@RequestBody JWTRequest authenticationRequest) throws Exception {
		final Estabelecimento estabelecimento = estabelecimentoRepository.loginEstabelecimento(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		if (estabelecimento != null) {
			final String token = jwtTokenUtil.generateTokenRestaurante(estabelecimento);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");

	}
	

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}