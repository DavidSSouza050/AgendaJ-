package lumicode.agendaja.api.jwtme.security;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lumicode.agendaja.api.model.Cliente;
import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.repository.ClienteRepository;
import lumicode.agendaja.api.repository.EstabelecimentoRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente c = clienteRepository.verificarEmail(email);
		Estabelecimento r = estabelecimentoRepository.verificarEmail(email);
		
		if (c != null) {
			User u = new User(c.getEmail(), c.getIdCliente().toString(), new ArrayList<>());

			return u;
		} else if (r != null) {
			User u = new User(r.getEmail(), r.getIdEstabelecimento().toString(), new ArrayList<>());

			return u;
		}
		throw new UsernameNotFoundException("User not found with username: " + email);

	}
}