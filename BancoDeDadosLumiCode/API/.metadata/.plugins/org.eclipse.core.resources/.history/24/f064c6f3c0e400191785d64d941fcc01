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


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = {"eventBusIndex", "verbose"})
public class JwtAuthenticationResource {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Autowired
	ConsumidorRepository consumidorRepository;
		
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@PostMapping("/login/consumidor")
	public ResponseEntity<?> createAuthenticationTokenConsumidor(@RequestBody JWTRequest authenticationRequest) throws Exception {
		final Consumidor consumidor = consumidorRepository.getConsumidorByEmailAndPass(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		if (consumidor != null) {
			final String token = jwtTokenUtil.generateTokenConsumidor(consumidor);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");

	}
	
	@PostMapping("/login/restaurante")
	public ResponseEntity<?> createAuthenticationTokenRestaurante(@RequestBody JWTRequest authenticationRequest) throws Exception {
		final Restaurante restaurante = restauranteRepository.getRestauranteByEmailAndPass(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		if (restaurante != null) {
			final String token = jwtTokenUtil.generateTokenRestaurante(restaurante);
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