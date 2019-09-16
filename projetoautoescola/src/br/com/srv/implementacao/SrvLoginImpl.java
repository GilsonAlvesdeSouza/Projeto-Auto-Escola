package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryLogin;
import br.com.srv.interfaces.SrvLogin;

@Service
public class SrvLoginImpl implements SrvLogin {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RepositoryLogin repositoryLogin;

	@Override
	public boolean autentico(String login, String Senha) throws Exception {
		// TODO Auto-generated method stub
		return repositoryLogin.autentico(login, Senha);
	}

}
