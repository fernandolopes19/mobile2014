package br.ufg.inf.fs.es.mobile.snaufg_servidor;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.util.Scanner;

public class EnviaMensagem {

	private static final String ID_DISPOSITIVO_GCM = "APA91bG1ck2IP91QFF6BokSow9lType3uMTm-id-V-UL5rFpCU5WAzIxImYF0VD71-fQ3Msw6cCvlwKZruwAkpfO7YpWlnAS6G45BaaAHdDcQ2inNEHi8QgWV24oWTVTiuq2QHcQrgxj21XgVoTCkLZ53IWEDT4qPB_sKTEtm6HaqzcgLHB4jPg";
	private static final String API_KEY = "AIzaSyBBtaDqKKDZMyohPLboAU0polwE3j93VlY";

	static final int TIME_TO_LIVE = 86400; // 86.400 segundos = 1 dia
	static final int TAMANHO_MENSAGEM = 1024; // 1024 caracteres = 2Kb 

	public static void main(String[] args) {

		ConexaoBD conexaoBD = new ConexaoBD();
		conexaoBD.criarConexao();
		conexaoBD.setNomeTabela("SNA-UFG");
		conexaoBD.criarTabela();
		conexaoBD.inserirDado(API_KEY, ID_DISPOSITIVO_GCM);

		Scanner scx = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		ObterHora obterHora = new ObterHora();

		String idDispositivoGCM = ID_DISPOSITIVO_GCM;
		String apiKey = API_KEY;
		String[] listaIdDispositivoGCM = null;

		int opcaoMenu1;
		int opcaoMenu2;
		String mensagemParaEnviar;
		do {

			System.out.println("O que você deseja fazer:"
					+ "\n1:Enviar mensagem" + "\n2:Mudar configuração"
					+ "\n0:Sair");
			opcaoMenu1 = sc.nextInt();

			switch (opcaoMenu1) {
			case 1:
				System.out.println("Digite seu nome: ");
				mensagemParaEnviar = scx.nextLine();
				mensagemParaEnviar += "\n";
				mensagemParaEnviar += obterHora.obterHora();
				mensagemParaEnviar += "\n";
				System.out.println("Digite a mensagem: ");
				mensagemParaEnviar += scx.nextLine();

				if (mensagemParaEnviar.length() < TAMANHO_MENSAGEM) {
					Sender sender = new Sender(apiKey);

					Message messagemGCM = new Message.Builder()
							.collapseKey("1").timeToLive(TIME_TO_LIVE)
							.delayWhileIdle(true)
							.addData("mensagem", mensagemParaEnviar).build();

					Result resultado = null;

					// Envia a mensagem completa para o dispositivo
					try {
						resultado = sender.send(messagemGCM, idDispositivoGCM,
								1);
						System.out.println("Enviou");
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (resultado != null) {
						System.out.println(resultado.toString());
					}

					break;
				} else{
					System.out.println("Mensagem muito grande.");
					break;
				}

			case 2:
				System.out.println("O que deseja fazer:"
						+ "\n1:Inserir novos dados"
						+ "\n2:Buscar ID Dispositivo" + "\n0:Sair");
				opcaoMenu2 = sc.nextInt();

				if (opcaoMenu2 == 1) {
					System.out.println("Digite API KEY: ");
					apiKey = scx.nextLine();
					System.out.println("Digite ID Dispositivo: ");
					idDispositivoGCM = scx.nextLine();

					conexaoBD.inserirDado(apiKey, idDispositivoGCM);
					break;
				} else if (opcaoMenu2 == 2) {
					listaIdDispositivoGCM = conexaoBD.buscarIdDispositivo();
					for(int i=0; i<listaIdDispositivoGCM.length;i++){
						System.out.println(listaIdDispositivoGCM[i]);
					}
					
					break;
				}
			case 0:
				break;
			default:
				System.out.println("Digite novamente.");
			}

		} while (opcaoMenu1 != 0);

	}
}
