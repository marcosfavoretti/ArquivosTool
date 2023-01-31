import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.nio.file.*;

//tentar pegar a extensao e nao precisar falar a extensao no txt
public class Rename {

	private File file;
	private String caminho;
	private String extensao;// extensao do arquivo

	public Rename(String path) throws Exception {

		this.file = new File(path);// estancio a file passadno o caminho que foi pedido
		this.caminho = path;

		if (!file.exists()) {// se o caminho nao for achado
			JOptionPane.showMessageDialog(null, "Caminho nao encontrado", "Mensagem", JOptionPane.WARNING_MESSAGE);
			this.file = null;
		}
	}

	public void Renomear(String namenovo, String[] listarq) {

		for (int i = 0; listarq.length > i; i++) {

			this.file = new File(this.caminho + "\\" + listarq[i]);// estancio de novo o private file

			this.extensao = (String) file.toString().subSequence(file.toString().indexOf("."),
					file.toString().length()); // verifico a ext de cada arquivo

			System.out.println(file);

			File newname = new File(this.caminho + "\\" + namenovo + "-" + (i + 1) + this.extensao);

			if (!newname.toString().equals("") || !newname.toString().equals("null")) {
				if (file.renameTo(newname) == true) {
					System.out.println("nome alterado");
				} else {
					System.out.println("nao foi possivel<---------------------------------------------------");
				}
			}
		}
	}

	public void Renomear(String[] listarq, ArrayList<String> listaformatada) {

		for (int i = 0; listarq.length > i; i++) {
			for (int j = 0; listaformatada.size() > j; j++) {

				if (listarq[i].indexOf(listaformatada.get(j).substring(listaformatada.get(j).indexOf("."))) != -1) {// se
																													// o

					this.file = new File(this.caminho + "\\" + listarq[i]);// estancio de novo o private file

					this.extensao = (String) file.toString().subSequence(file.toString().indexOf("."),

							file.toString().length()); // verifico a ext de cada arquivo

					File newname = new File(this.caminho + "\\" + listaformatada.get(j));
					System.out.println("->>>>>" + newname);
					if (!newname.toString().equals("") || !newname.toString().equals("null")) {
						if (file.renameTo(newname) == true) {// muda o nome do arquivo e mostra a msg se der certo
							System.out.println("nome alterado");
							listaformatada.remove(j);
						} else {
							System.out.println(
									"nao foi possivel<--------------------------------------------------------");
						}
					}

				} else {
					System.out.println("->>>>>" + listarq[i] + "================>>>>> nao mudado");
				}
			}
		}
	}

//
	public String[] arquivos() {// ordem de arquivos em ordem de numeric

		int cont = 1, continfo = 0;

		String[] arquivoslist = this.file.list();// todos os arquivos em ordem aleatoria

		return arquivoslist;

	}

	public String[] organiza(String[] arquivoslist) {

		String[] auxlist = new String[arquivoslist.length];// auxiliar para pega a sequencia certa dos Strings
		int continfo = 0, ultimocamp = -1;

		for (int i = 0; arquivoslist.length > i; i++) {// verifica se no nome tem numeros
			for (int j = 0; 10 > j; j++) {
				if (arquivoslist[i].indexOf(Integer.toString(j)) == -1) {
				} else {
					int resta = 10 - j;
					continfo++;// para saber o tamanho do vetor final que preciso fazer
					j = j + resta;// para nao gastar tempo verificando o resto dos numero do for pq so preciso
									// saber se existe qlq numero

					try {
						char um = arquivoslist[i].charAt(arquivoslist[i].indexOf(".") - 1);

						try {
							char dois = arquivoslist[i].charAt(arquivoslist[i].indexOf(".") - 2);

							String fim = Character.toString(dois) + Character.toString(um);
							auxlist[Integer.parseInt(fim) - 1] = arquivoslist[i];// mudar
							System.out.println("apenas dois numero ____________________");

						} catch (Exception y) {
							Character.getNumericValue(um);
							System.out.println("apenas um numero ____________________");
							auxlist[Character.getNumericValue(um) - 1] = arquivoslist[i];// mudar
						}

					} catch (Exception e) { //

						System.out.println("nome entrou em excessao" + arquivoslist[i]);
						auxlist[arquivoslist.length + ultimocamp] = arquivoslist[i];// pode ocorrer de um numero nao ter
																					// numeros bugando aki
						ultimocamp--;
					}

				}
			}
		}
		// so pode ser ou esta entrando em um try ou esta nao encontrando numeros

		String[] finallist = new String[continfo];

		for (int y = 0; finallist.length > y; y++) {
			if (auxlist != null)
				finallist[y] = auxlist[y];
		}

		paraString(finallist);
		return finallist;

	}

	public void formatacao(String[] listarquivos) {

		int cont = 1, continfo = 0;

		// String [] nomeformatado = new String [listarquivos.length ]; //cria uma nova
		// lista para colocar os nomes ja formatados

		ArrayList<String> nomefomatado = new ArrayList();

		for (int i = 0; listarquivos.length > i; i++) {// melhorar isso mais para frente

			String temp = listarquivos[i];

			try {
				this.extensao = temp.substring(temp.indexOf("."));// pega a extensao ja aki
				String remove = temp.substring(temp.indexOf("("));
				System.out.println("oq vai ser removido" + remove);
				temp = temp.replace(remove, "");// ja trata o dados

				try {

					temp = temp.replace("_", "");

				} catch (Exception underline) {
					// nao tem underline mais e ja pode ser enviada
				}

//		
				nomefomatado.add(temp.replace(" ", "") + this.extensao);

			}

			catch (Exception barra) {
				System.out.println("nome ja formatado");
			}

		}
		paraString(nomefomatado);// teste para ver como vai ficar o nome depois de alterado

		// enviar para o metodo renome
		Renomear(listarquivos, nomefomatado);// passar lista de arquivos depois como q gostaria de por no o nome depois
												// de formatado
	}

	public void paraString(String[] arquivos) {// metodos para imprimir o nomedo arquivos

		System.out.println("->>>" + arquivos.length);// quantidade de arquivos encontrados

		for (int i = 0; arquivos.length > i; i++) {
			System.out.println("->>" + arquivos[i]);
		}

	}

	public void paraString(ArrayList<String> arquivos) {
		System.out.println("->>>" + arquivos.size());
		for (String i : arquivos) {
			System.out.println("->>" + i);
		}

	}

	public String[] leTxt() throws IOException {// index of para achar o lugar o caminho do txt

		String[] nomearquivos;

		String temp = "";

		FileReader fr = new FileReader(this.file);

		BufferedReader br = new BufferedReader(fr);

		while (br.ready()) {
			temp += br.readLine() + "&";
		}

		nomearquivos = temp.split("&");// pasa os nomes dos arquivos lidos para o vetor

		paraString(nomearquivos);

		return nomearquivos;
	}

	public void mover(String[] arquivos, String endfinal) {// podemos para resolver isso dar .list e ir analisando por
															// cada parte do vetor

		for (int j = 0; arquivos.length > j; j++) {
			arquivos[j] = arquivos[j];// perguntar se vai ser falado a extensoa no txt
		}

		paraString(arquivos);

		File filefinal = new File(endfinal);

		for (int i = 0; arquivos.length > i; i++) {

			this.file = new File(arquivos[i]);
			System.out.println(file.toString());
			System.out.println(filefinal);

			boolean ok = file.renameTo(new File(endfinal, file.getName()));// mover arquivos de um pasta para outra

			if (ok == true) {
				System.out.println("movido com sucesso");

			} else {
				System.out.println("problema");
			}
		}

	}

	public File getFile() {// pega infos dos gets
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}

//BONUS: usando duas matrizes e indo organizando em uma matriz e no final passar limpo para outra
//tratar quando um dado e so formatado ficano dai com numeros
//filtro de repetidos
//so fazer com q ele ache a extensao