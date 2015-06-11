package SocketServer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Principal {
	static srvRecebeCnxSocket umServidor;
	

	public static void main(String[] args) {
		new jframe();

	}

	public static class jframe extends JFrame implements cnxSocketListner {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public jframe() {
			cnxSocketListner sl = this;
			int porta = 1234;
			setTitle("EXEMPLO");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new FlowLayout());
			setVisible(true);
			setBounds(200, 200, 250, 200);

			JButton jbutton1 = new JButton("Iniciar Servidor");
			JButton jbutton2 = new JButton("Encerrar Servidor");
			jbutton2.setEnabled(false);

			

			jbutton1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					umServidor = new srvRecebeCnxSocket(sl,porta);
					jbutton1.setEnabled(false);
					jbutton2.setEnabled(true);
					new Thread(umServidor).start();
				}
			});

			jbutton2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String ip;
					try {
						ip = InetAddress.getLocalHost().getHostAddress();
						umServidor.setTerminar();
						new Socket(ip, porta);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					jbutton1.setEnabled(true);
					jbutton2.setEnabled(false);
				}
			});

			add(jbutton1);
			add(jbutton2);
		}

		@Override
		public void ChegouNovaCNX(Socket novaCNX) {
			// TODO Auto-generated method stub

		}
	}

}
