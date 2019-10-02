package lumicode.agendaja.api.jwtme.security;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lumicode.agendaja.api.model.Cliente;
import lumicode.agendaja.api.model.Estabelecimento;
@Component
public class JwtTokenUtill implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//intermediario da geracao do token
	public String generateTokenConsumidor(Cliente cliente) {
		Map<String, Object> claims = new HashMap<>();
		
		return doGenerateToken(claims, cliente.getEmail(), cliente.getIdCliente());
	}
	
	//intermediario da geracao do token
		public String generateTokenRestaurante(Estabelecimento estabelecimento) {
			Map<String, Object> claims = new HashMap<>();
			
			return doGenerateToken(claims, estabelecimento.getEmail(), estabelecimento.getIdEstabelecimento());
		}


	//gerando o tokem com o id e nome do usuario 
	private String doGenerateToken(Map<String, Object> claims, String subject, Long id) {
		
		System.out.println(subject + "generate token");
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}