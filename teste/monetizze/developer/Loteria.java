package teste.monetizze.developer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Loteria {

	public static void main(String[] args) {		
		Jogo j = new Jogo(10, 10);
		j.gerarJogos();
		j.realizarSorteio();
		
		Loteria.imprimirResultados(j.getJogos(), j.getResultado());
	}
	
	public static void imprimirResultados(List<ArrayList<Integer>> jogos, List<Integer> resultado) {
		try {
			List<String> resultadoFinal = Loteria.listIntToString(resultado);
            StringBuilder htmlStringBuilder = new StringBuilder();
            
            // Header
            htmlStringBuilder.append("<html><head><title>Monetizze - Developer Test</title></head>");
            htmlStringBuilder.append("<body>");
            
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
            htmlStringBuilder.append("<tr><td bgcolor=\"#2060A8\" colspan=\"10\" align=\"center\"><b><font color=\"#FFFFFF\"> Jogos Realizados </b></td></tr>");
            
            // Content
            for(List<Integer> jogo: jogos) {
            	List<String> jogoEmStrings = Loteria.listIntToString(jogo);
            	htmlStringBuilder.append("<tr>");
            	
            	for(String dezena: jogoEmStrings) {
            		if(resultadoFinal.contains(dezena)) {
            			htmlStringBuilder.append("<td colspan=\"1\"><b><font color=\"#75AE40\">" + dezena + "</td>");
            		} else {
            			htmlStringBuilder.append("<td colspan=\"1\"><b><font color=\"#000000\">" + dezena + "</td>");
            		}
            	}            	
            	htmlStringBuilder.append("</tr>");            	
            }            
            
            // Footer
            htmlStringBuilder.append("<tr><td align=\"center\" bgcolor=\"#2060A8\" colspan=\"10\"><b><font color=\"#FFFFFF\"> Resultado do Sorteio! </b></td></tr>");
            htmlStringBuilder.append("<tr>");            
            
            for(String dezena: resultadoFinal) {
            	htmlStringBuilder.append("<td colspan=\"1\"><b><font color=\"#75AE40\">" + dezena + "</td>");
            }           
            htmlStringBuilder.append("</tr>");
            
            // Closing file
            htmlStringBuilder.append("</table></body></html>");
            WriteToFile(htmlStringBuilder.toString(),"resultadoSorteio.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator + fileName;
        File file = new File(tempFile);
        
        if (file.exists()) {
            try {
                File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();
        System.out.println("Concluido!");
    }
	
	public static List<String> listIntToString(List<Integer> dezenas) {
		List<String> emString = new ArrayList<String>();
		
		for(Integer dezena: dezenas) {
			if(dezena < 10) {
				String novaDezena = "0" + dezena;
				emString.add(novaDezena);
			} else {
				String novaDezena = "" + dezena;
				emString.add(novaDezena);
			}
		}		
		return emString;
	}
}
