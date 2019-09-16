package br.com.project.geral.controller;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionControllerImpl implements SessionController {

	private static final long serialVersionUID = 1L;

	private HashMap<String, HttpSession> hasMap = new HashMap<>();

	@Override
	public void addSession(String keyLoginUser, HttpSession httpSession) {
		hasMap.put(keyLoginUser, httpSession);

	}

	@Override
	public void invalidateSession(String keyLoginUser) {
		HttpSession session = hasMap.get(keyLoginUser);
		if (session != null) {// remove sessão do usuário passado por um parametro
			try {
				session.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Não tem usuário");
		}
		hasMap.remove(keyLoginUser);

	}

}
