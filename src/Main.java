import java.io.File;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws Exception {
		Rename rename = null;
		String opcao;

		do {
			opcao = JOptionPane.showInputDialog(
					"___________________________________________________________\n   "
					+ "                                                "
					+ " MENU\n___________________________________________________________\n"
					+ "\nA] Renomear os arquivos\nB] Formatar os arquivos\nC] Mover\nD] SAIR\n\n");

			if (opcao.toUpperCase().equals("A") || opcao.toUpperCase().equals("B") || opcao.toUpperCase().equals("C")
					|| opcao.toUpperCase().equals("D")) {// so pode-se passar a b ou c

			}
			if (opcao.toUpperCase().equals("A")) {// caso queira renomear o arquivo

				do {

					String path = JOptionPane.showInputDialog("Caminha da pasta que vc quer renomear os arquivos: ");

					rename = new Rename(path);// pasar o caminho para o construtor

				} while (rename.getFile() == null);// so sai do do while quano for !null

				String[] volta = rename.arquivos();
				volta  = rename.organiza(volta);
				
				if (volta.length != 0) {

					String name = JOptionPane.showInputDialog("Nome estaticos que deseja implementar: ");
					if (!(name == null)) {

						try {
							rename.Renomear(name, volta); //mando a ordem e o nomes
						} catch (Exception tentativa) {
							System.out.println("Nao foi possivel subsituir os arquivos para o nome: '" + name + "'");
						}
					}

					// tratar a lista de arquivos em ordem}

				}

			} else if (opcao.toUpperCase().equals("B")) {// caso queira so formatar

				do {

					String path = JOptionPane.showInputDialog("Caminha da pasta que vc quer renomear os arquivos: ");

					rename = new Rename(path);// pasar o caminho para o construtor

				} while (rename.getFile() == null);// so sai do do while quano for !null
				
				rename.paraString(rename.arquivos());
				
				rename.formatacao(rename.arquivos());

			}

			else if (opcao.toUpperCase().equals("C")) {
				// pedi caminho final, ler txt com nomes dos arquivos procurar e move os
				// arquivos com nomes igual

			


				do {

					String caminhoinicial = JOptionPane
							.showInputDialog("Caminho completo do txt com a lista de arquivos vao ser movidos");

					rename = new Rename(caminhoinicial);

				} while (rename.getFile() == null);// so sai do do while quando for !null
				
				String caminhofinal = JOptionPane.showInputDialog("Caminho onde os arquivos seram movidos");

				try {
				
						String [] nome  = rename.leTxt();
						
						rename.mover(nome , caminhofinal);//vai passar o file so que sem a extensao

					}
				 catch (Exception a) {
					
					System.out.println("nao gerou "+ a);//geralmente da esse erro pq nao tem permissao
					//nao conseguir gerar o fil ecom o url dado
				}

			}
		} while (!opcao.toUpperCase().equals("D"));
	}

}
//botar parametro de nome facer um varivael de cont para sempre ir adicionando os numeros

//teste caminho 
// C:\Users\marco\Documents\teste

//acesso negado 

//pegar o dado e verificar se existe numero se nao tiver:
// passa para outra pasta