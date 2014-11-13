package web.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/endpoint")
public class EndPoint {

	//Forsøg på websocket endpoint, til brug i med opdatering af liste
	
	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public String onMessage(String message) {
		return null;
	}

	@OnOpen
	public void onOpen (Session peer) {
		peers.add(peer);
	}

	@OnClose
	public void onClose (Session peer) {
		peers.remove(peer);
	}

}
