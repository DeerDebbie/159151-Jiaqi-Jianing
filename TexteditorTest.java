package edit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TexteditorTest {

	@Test
	void testOpen(BufferedReader br,JTextArea ta) throws IOException {
		File file = new File("src/edit/pmd-eclipse");  
        FileReader fr = new FileReader(file);  
        BufferedReader br1 = new BufferedReader(fr);  
		ta.setText(""); 
        String text;  
        while ((text = br1.readLine()) != null) {  
            ta.append(text); 
            ta.append("\n"); 
    }  
	}

	@Test
	void testSave(FileOutputStream out,JTextArea ta) throws IOException {
		out.write(ta.getText().getBytes());
	}

	@Test
	void testSearch(String str_search,JTextArea ta) {
		int len = str_search.length();
		int key=0;
        for (int i = key; i < ta.getText().length() - len + 1; i++) {  
            String str_record = ta.getText().substring(i, i + len);  
            if (str_record.equals(str_search)) {  
                key = i + 1;  
                ta.requestFocus();  
                ta.select(i, i + len);  
                   }
            }
	}

}
