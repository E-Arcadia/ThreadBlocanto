package SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class srvRecebeCnxSocket implements Runnable {
	private cnxSocketListner umOuvidor;
	private boolean terminar = true;
	private int porta;

	public srvRecebeCnxSocket(cnxSocketListner umOuvidor, int porta) {
		this.umOuvidor = umOuvidor;
		this.porta = porta;

	}

	@Override
	public void run() {
		ServerSocket servidor;
		Socket umSocket = null;
		while (terminar) {
			try {
				servidor = new ServerSocket(porta);
				System.err.println("Aguardando conex�o!!");
				umSocket = servidor.accept();
				umOuvidor.ChegouNovaCNX(umSocket);
			} catch (IOException e) {
				System.out.println("Porta ainda ocupada. Aguardando libera��o do BIND.");
			}
			System.err.println("Vai validar se continua no La�o");
		}
		System.err.println("Saiu do la�o. \nVai terminar a thread");
	}

	public void setTerminar() {
		this.terminar = false;
	}
}
